package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import eCensus.baza.ConnectionPool;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.KorisnikSistema;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class MySQLAdministratorAgencijeDAO implements AdministratorAgencijeDAO {

	@Override
	public Collection<KorisnikSistema> getListuKorisnika() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			Statement statementAdministratorAgencije = connection.createStatement();
			ResultSet resultSet = statementAdministratorAgencije.executeQuery(
													"SELECT * " +
													"FROM osoba Osoba " + 
													"INNER JOIN administrator_agencije AdministratorAgencije on Osoba.IdOsobe = AdministratorAgencije.IdOsobe;");
			
			ArrayList<KorisnikSistema> administratoriAgencije = new ArrayList<>();
			while(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				AdministratorAgencije administratorAgencije = new AdministratorAgencije(
													resultSet.getString("Ime"),
													resultSet.getString("Prezime"),
													resultSet.getString("KorisnickoIme"),
													resultSet.getString("Lozinka"),
													JEZIK.getJEZIK(jezik),
													PISMO.getPISMO(pismo),
													resultSet.getString("NazivAgencije"));
				administratorAgencije.setId(resultSet.getInt("IdOsobe"));
				administratoriAgencije.add(administratorAgencije);
			}
			
			return administratoriAgencije;
			
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
			PreparedStatement preparedStatementAdministratorAgencije = connection.prepareStatement( 
					"DELETE FROM administrator_agencije WHERE IdOsobe = ?; " + 
					"DELETE FROM administrator WHERE IdOsobe = ?; " + 
					"DELETE FROM osoba WHERE IdOsobe = ?; " );
			preparedStatementAdministratorAgencije.setLong(1, id);
			preparedStatementAdministratorAgencije.setLong(2, id);
			preparedStatementAdministratorAgencije.setLong(3, id);
			preparedStatementAdministratorAgencije.executeUpdate();
			preparedStatementAdministratorAgencije.close();
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
		AdministratorAgencije administratorAgencije = (AdministratorAgencije) korisnik;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementAdministratorAgencije = connection.prepareStatement(
					"INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka,Jezik,Pismo) values (?,?,?,?,?,?); " + 
					"INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID()); " + 
					"INSERT INTO administrator_agencije(IdOsobe,Naziv) values (LAST_INSERT_ID(),?); ");
			preparedStatementAdministratorAgencije.setString(1, administratorAgencije.getIme());
			preparedStatementAdministratorAgencije.setString(2, administratorAgencije.getPrezime());
			preparedStatementAdministratorAgencije.setString(3, administratorAgencije.getKorisnickoIme());
			preparedStatementAdministratorAgencije.setString(4, administratorAgencije.getLozinkaHash());
			preparedStatementAdministratorAgencije.setString(5, administratorAgencije.getJezik().toString());
			preparedStatementAdministratorAgencije.setString(6, administratorAgencije.getPismo().toString());
			preparedStatementAdministratorAgencije.setString(7, administratorAgencije.getIme());//Naziv agencije
			preparedStatementAdministratorAgencije.executeUpdate();
			preparedStatementAdministratorAgencije.close();
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
			PreparedStatement preparedStatementAdministratorAgencije = connection.prepareStatement( 
					"UPDATE osoba " + 
					"SET Ime = ?, " + 
					"	 Prezime = ?, " + 
					"    KorisnickoIme = ?, " + 
					"    LozinkaHash = ?, " +
					"	 Jezik = ?, " + 
					"	 Pismo = ? " + 
					"WHERE IdOsobe = ?;");
			preparedStatementAdministratorAgencije.setString(1, korisnik.getIme());
			preparedStatementAdministratorAgencije.setString(2, korisnik.getPrezime());
			preparedStatementAdministratorAgencije.setString(3, korisnik.getKorisnickoIme());
			preparedStatementAdministratorAgencije.setString(4, korisnik.getLozinkaHash());
			preparedStatementAdministratorAgencije.setString(5, korisnik.getJezik().toString());
			preparedStatementAdministratorAgencije.setString(6, korisnik.getPismo().toString());
			preparedStatementAdministratorAgencije.setLong(7, korisnik.getId());
			preparedStatementAdministratorAgencije.executeUpdate();
			preparedStatementAdministratorAgencije.close();
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
													"INNER JOIN administrator_agencije AdministratorAgencije on Osoba.IdOsobe = AdministratorAgencije.IdOsobe " +
													"WHERE Osoba.KorisnickoIme = ?");
			preparedStatementPopisivac.setString(1, korisnickoIme);
			ResultSet resultSet = preparedStatementPopisivac.executeQuery();
			
			KorisnikSistema korisnikSistema = null;
			
			if(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				korisnikSistema = new AdministratorAgencije(resultSet.getString("Ime"),
															resultSet.getString("Prezime"),
															resultSet.getString("KorisnickoIme"),
															resultSet.getString("Lozinka"),
															JEZIK.getJEZIK(jezik),
															PISMO.getPISMO(pismo),
															resultSet.getString("NazivAgencije"));
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
