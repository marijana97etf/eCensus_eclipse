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
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class MySQLPopisivacDAO implements PopisivacDAO {

	@Override
	public Collection<KorisnikSistema> getListuKorisnika() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			Statement statementPopisivac = connection.createStatement();
			ResultSet resultSet = statementPopisivac.executeQuery(
													"SELECT * " +
													"FROM osoba Osoba " + 
													"INNER JOIN popisivac Popisivac on Osoba.IdOsobe = Popisivac.IdOsobe;");
			
			ArrayList<KorisnikSistema> popisivaci = new ArrayList<>();
			while(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				Popisivac popisivac = new Popisivac(/*resultSet.getInt("IdOsobe")*/"",
													resultSet.getString("Ime"),
													resultSet.getString("Prezime"),
													resultSet.getString("KorisnickoIme"),
													resultSet.getString("Lozinka"),
													JEZIK.getJEZIK(jezik),
													PISMO.getPISMO(pismo));
				popisivac.setLozinkaHash(resultSet.getString("Lozinka"));
				popisivaci.add(popisivac);
			}
			
			return popisivaci;
			
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
			PreparedStatement preparedStatementPopisivac = connection.prepareStatement( 
					"DELETE FROM popisivac WHERE IdOsobe = ? ;" + 
					"DELETE FROM osoba WHERE IdOsobe = ?; " );
			preparedStatementPopisivac.setLong(1, id);
			preparedStatementPopisivac.setLong(2, id);
			preparedStatementPopisivac.executeUpdate();
			preparedStatementPopisivac.close();
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
		Popisivac popisivac = (Popisivac) korisnik;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisivac = connection.prepareStatement(
					"INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka,Jezik,Pismo) values (?,?,?,?,?,?); " + 
					"INSERT INTO popisivac(IdOsobe) values (LAST_INSERT_ID()); ");
			preparedStatementPopisivac.setString(1, popisivac.getIme());
			preparedStatementPopisivac.setString(2, popisivac.getPrezime());
			preparedStatementPopisivac.setString(3, popisivac.getKorisnickoIme());
			preparedStatementPopisivac.setString(4, popisivac.getLozinkaHash());
			preparedStatementPopisivac.setString(5, popisivac.getJezik().toString());
			preparedStatementPopisivac.setString(6, popisivac.getPismo().toString());
			preparedStatementPopisivac.executeUpdate();
			preparedStatementPopisivac.close();
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
			PreparedStatement preparedStatementPopisivac = connection.prepareStatement( 
					"UPDATE osoba " + 
					"SET Ime = ?, " + 
					"	 Prezime = ?, " + 
					"    KorisnickoIme = ?, " + 
					"    LozinkaHash = ?, " +
					"	 Jezik = ?, " + 
					"	 Pismo = ? " + 
					"WHERE IdOsobe = ?;");
			preparedStatementPopisivac.setString(1, korisnik.getIme());
			preparedStatementPopisivac.setString(2, korisnik.getPrezime());
			preparedStatementPopisivac.setString(3, korisnik.getKorisnickoIme());
			preparedStatementPopisivac.setString(4, korisnik.getLozinkaHash());
			preparedStatementPopisivac.setString(5, korisnik.getJezik().toString());
			preparedStatementPopisivac.setString(6, korisnik.getPismo().toString());
			preparedStatementPopisivac.setLong(7, korisnik.getId());
			preparedStatementPopisivac.executeUpdate();
			preparedStatementPopisivac.close();
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
													"INNER JOIN popisivac Popisivac on Osoba.IdOsobe = Popisivac.IdOsobe " +
													"WHERE Osoba.KorisnickoIme = ? ");
			preparedStatementPopisivac.setString(1, korisnickoIme);
			ResultSet resultSet = preparedStatementPopisivac.executeQuery();
			
			KorisnikSistema korisnikSistema = null;
			
			if(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				korisnikSistema = new Popisivac(/*resultSet.getInt("IdOsobe")*/"",
													resultSet.getString("Ime"),
													resultSet.getString("Prezime"),
													resultSet.getString("KorisnickoIme"),
													resultSet.getString("Lozinka"),
													JEZIK.getJEZIK(jezik),
													PISMO.getPISMO(pismo));
			}
			return korisnikSistema;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}
	
	@Override
	public boolean dodajPopisniKrug() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean azurirajPopisniKrug() {
		// TODO Auto-generated method stub
		return false;
	}

}
