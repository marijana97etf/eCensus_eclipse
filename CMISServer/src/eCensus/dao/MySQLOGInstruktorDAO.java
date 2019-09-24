package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import eCensus.baza.ConnectionPool;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class MySQLOGInstruktorDAO implements OGInstruktorDAO {

	@Override
	public Collection<KorisnikSistema> getListuKorisnika() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			Statement statementOGInstruktor = connection.createStatement();
			ResultSet resultSet = statementOGInstruktor.executeQuery(
													"SELECT * " +
													"FROM osoba Osoba " + 
													"INNER JOIN og_instruktor OGInstruktor on Osoba.IdOsobe = OGInstruktor.IdOsobe;");
			
			ArrayList<KorisnikSistema> ogInstruktori = new ArrayList<>();
			while(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				OGInstruktor ogInstruktor = new OGInstruktor(resultSet.getString("Ime"),
															 resultSet.getString("Prezime"),
															 resultSet.getString("KorisnickoIme"),
															 resultSet.getString("Lozinka"),
															 JEZIK.getJEZIK(jezik),
															 PISMO.getPISMO(pismo),
															 resultSet.getString("Grad"),
															 resultSet.getString("Opstina"));
				ogInstruktor.setId(resultSet.getInt("IdOsobe"));
				ogInstruktori.add(ogInstruktor);
			}
			
			return ogInstruktori;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

	@Override
	public boolean obrisiKorisnika(long id) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementOGInstruktor = connection.prepareStatement( 
					"DELETE FROM administrator_agencije WHERE IdOsobe = ?; " + 
					"DELETE FROM administrator WHERE IdOsobe = ?; " + 
					"DELETE FROM og_instruktor WHERE IdOsobe = ?; " );
			preparedStatementOGInstruktor.setLong(1, id);
			preparedStatementOGInstruktor.setLong(2, id);
			preparedStatementOGInstruktor.setLong(3, id);
			preparedStatementOGInstruktor.executeUpdate();
			preparedStatementOGInstruktor.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}

	@Override
	public boolean dodajKorisnika(KorisnikSistema korisnik) {
		OGInstruktor ogInstruktor = (OGInstruktor) korisnik;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementOGInstruktor = connection.prepareStatement(
					"INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka,Jezik,Pismo) values (?,?,?,?,?,?); " + 
					"INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID()); " + 
					"INSERT INTO og_instruktor(IdOsobe,Grad,Opstina) values (LAST_INSERT_ID(),?,?); ");
			preparedStatementOGInstruktor.setString(1, ogInstruktor.getIme());
			preparedStatementOGInstruktor.setString(2, ogInstruktor.getPrezime());
			preparedStatementOGInstruktor.setString(3, ogInstruktor.getKorisnickoIme());
			preparedStatementOGInstruktor.setString(4, ogInstruktor.getLozinkaHash());
			preparedStatementOGInstruktor.setString(5, ogInstruktor.getJezik().toString());
			preparedStatementOGInstruktor.setString(6, ogInstruktor.getPismo().toString());
			preparedStatementOGInstruktor.setString(7, ogInstruktor.getIme());//Grad
			preparedStatementOGInstruktor.setString(8, ogInstruktor.getIme());//Opstina
			preparedStatementOGInstruktor.executeUpdate();
			preparedStatementOGInstruktor.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
		
	}

	@Override
	public boolean azurirajKorisnika(KorisnikSistema korisnik) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementOGInstruktor = connection.prepareStatement( 
					"UPDATE osoba " + 
					"SET Ime = ?, " + 
					"	 Prezime = ?, " + 
					"    KorisnickoIme = ?, " + 
					"    LozinkaHash = ?, " +
					"	 Jezik = ?, " + 
					"	 Pismo = ? " + 
					"WHERE IdOsobe = ?;");
			preparedStatementOGInstruktor.setString(1, korisnik.getIme());
			preparedStatementOGInstruktor.setString(2, korisnik.getPrezime());
			preparedStatementOGInstruktor.setString(3, korisnik.getKorisnickoIme());
			preparedStatementOGInstruktor.setString(4, korisnik.getLozinkaHash());
			preparedStatementOGInstruktor.setString(5, korisnik.getJezik().toString());
			preparedStatementOGInstruktor.setString(6, korisnik.getPismo().toString());
			preparedStatementOGInstruktor.setLong(7, korisnik.getId());
			preparedStatementOGInstruktor.executeUpdate();
			preparedStatementOGInstruktor.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}

	@Override
	public boolean sadrziKorisnika(KorisnikSistema korisnik) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public KorisnikSistema getKorisnikSistema(String korisnickoIme) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisivac = connection.prepareStatement(
													"SELECT * " +
													"FROM osoba Osoba " + 
													"INNER JOIN og_instruktor OGInstruktor on Osoba.IdOsobe = OGInstruktor.IdOsobe " +
													"WHERE Osoba.KorisnickoIme = ? ");
			preparedStatementPopisivac.setString(1, korisnickoIme);
			ResultSet resultSet = preparedStatementPopisivac.executeQuery();
			
			KorisnikSistema korisnikSistema = null;
			
			if(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				korisnikSistema = new OGInstruktor(resultSet.getString("Ime"),
												   resultSet.getString("Prezime"),
												   resultSet.getString("KorisnickoIme"),
												   resultSet.getString("Lozinka"),
												   JEZIK.getJEZIK(jezik),
												   PISMO.getPISMO(pismo),
												   resultSet.getString("Grad"),
												   resultSet.getString("Opstina"));
				korisnikSistema.setId(resultSet.getInt("IdOsobe"));
			}
			return korisnikSistema;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}
	
}
