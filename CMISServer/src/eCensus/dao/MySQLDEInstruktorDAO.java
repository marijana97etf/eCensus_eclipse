package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import eCensus.baza.ConnectionPool;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.DEInstruktor.DRZAVA;
import model.korisnicki_nalozi.DEInstruktor.ENTITET;
import model.korisnicki_nalozi.KorisnikSistema;
import model.pracenje_popisa.PISMO;
import model.pracenje_popisa.JEZIK;

public class MySQLDEInstruktorDAO implements DEInstruktorDAO {

	@Override
	public Collection<KorisnikSistema> getListuKorisnika() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			Statement statementDEInstruktor = connection.createStatement();
			ResultSet resultSet = statementDEInstruktor.executeQuery(
													"SELECT * " +
													"FROM osoba Osoba " + 
													"INNER JOIN de_instruktor DEInstruktor on Osoba.IdOsobe = DEInstruktor.IdOsobe;");
			
			ArrayList<KorisnikSistema> deInstruktori = new ArrayList<>();
			while(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				DEInstruktor deInstruktor = new DEInstruktor(resultSet.getString("Ime"),
															 resultSet.getString("Prezime"),
															 resultSet.getString("KorisnickoIme"),
															 resultSet.getString("Lozinka"),
															 DRZAVA.getDRZAVA(resultSet.getString("Drzava")),
															 ENTITET.getENTITET(resultSet.getString("Entitet")),
															 JEZIK.getJEZIK(jezik),
															 PISMO.getPISMO(pismo));
				deInstruktor.setId(resultSet.getInt("IdOsobe"));
				deInstruktori.add(deInstruktor);
			}
			
			return deInstruktori;
			
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
			PreparedStatement preparedStatementDEInstruktor = connection.prepareStatement( 
					"DELETE FROM administrator_agencije WHERE IdOsobe = ?;" + 
					"DELETE FROM administrator WHERE IdOsobe = ?; " + 
					"DELETE FROM de_instruktor WHERE IdOsobe = ?; " );
			preparedStatementDEInstruktor.setLong(1, id);
			preparedStatementDEInstruktor.setLong(2, id);
			preparedStatementDEInstruktor.setLong(3, id);
			preparedStatementDEInstruktor.executeUpdate();
			preparedStatementDEInstruktor.close();
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
		DEInstruktor deInstruktor = (DEInstruktor) korisnik;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementDEInstruktor = connection.prepareStatement(
					"INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka,Jezik,Pismo) values (?,?,?,?,?,?); " + 
					"INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID()); " + 
					"INSERT INTO de_instruktor(IdOsobe,Drzava,Entitet) values (LAST_INSERT_ID(),?,?); ");
			preparedStatementDEInstruktor.setString(1, deInstruktor.getIme());
			preparedStatementDEInstruktor.setString(2, deInstruktor.getPrezime());
			preparedStatementDEInstruktor.setString(3, deInstruktor.getKorisnickoIme());
			preparedStatementDEInstruktor.setString(4, deInstruktor.getLozinkaHash());
			preparedStatementDEInstruktor.setString(5, deInstruktor.getJezik().toString());
			preparedStatementDEInstruktor.setString(6, deInstruktor.getPismo().toString());
			preparedStatementDEInstruktor.setString(7, deInstruktor.getDrzava().toString());//Drzava
			preparedStatementDEInstruktor.setString(8, deInstruktor.getEntitet().toString());//Entitet
			preparedStatementDEInstruktor.executeUpdate();
			preparedStatementDEInstruktor.close();
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
			PreparedStatement preparedStatementDEInstruktor = connection.prepareStatement( 
					"UPDATE osoba " + 
					"SET Ime = ?, " + 
					"	 Prezime = ?, " + 
					"    KorisnickoIme = ?, " + 
					"    LozinkaHash = ?, " +
					"	 Jezik = ?, " + 
					"	 Pismo = ? " + 
					"WHERE IdOsobe = ?;");
			preparedStatementDEInstruktor.setString(1, korisnik.getIme());
			preparedStatementDEInstruktor.setString(2, korisnik.getPrezime());
			preparedStatementDEInstruktor.setString(3, korisnik.getKorisnickoIme());
			preparedStatementDEInstruktor.setString(4, korisnik.getLozinkaHash());
			preparedStatementDEInstruktor.setString(5, korisnik.getJezik().toString());
			preparedStatementDEInstruktor.setString(6, korisnik.getPismo().toString());
			preparedStatementDEInstruktor.setLong(7, korisnik.getId());
			preparedStatementDEInstruktor.executeUpdate();
			preparedStatementDEInstruktor.close();
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
													"INNER JOIN de_instruktor DEInstruktor on Osoba.IdOsobe = DEInstruktor.IdOsobe " +
													"WHERE Osoba.KorisnickoIme = ?");
			preparedStatementPopisivac.setString(1, korisnickoIme);
			ResultSet resultSet = preparedStatementPopisivac.executeQuery();
			
			KorisnikSistema korisnikSistema = null;
			
			if(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				korisnikSistema = new DEInstruktor( resultSet.getString("Ime"),
													resultSet.getString("Prezime"),
													resultSet.getString("KorisnickoIme"),
													resultSet.getString("Lozinka"),
													DRZAVA.getDRZAVA(resultSet.getString("Drzava")),
													ENTITET.getENTITET(resultSet.getString("Entitet")),
													JEZIK.getJEZIK(jezik),
													PISMO.getPISMO(pismo));
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
