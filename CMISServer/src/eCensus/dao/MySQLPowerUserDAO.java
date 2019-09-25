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
import model.korisnicki_nalozi.PowerUser;

public class MySQLPowerUserDAO implements PowerUserDAO {

	@Override
	public Collection<KorisnikSistema> getListuKorisnika() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			Statement statementPowerUser = connection.createStatement();
			ResultSet resultSet = statementPowerUser.executeQuery(
													"SELECT * " +
													"FROM osoba Osoba " + 
													"INNER JOIN power_user PowerUser on Osoba.IdOsobe = PowerUser.IdOsobe;");
			
			ArrayList<KorisnikSistema> powerUsers = new ArrayList<>();
			while(resultSet.next()) {
				PowerUser powerUser = new PowerUser(
													resultSet.getString("Ime"),
													resultSet.getString("Prezime"),
													resultSet.getString("KorisnickoIme"),
													resultSet.getString("Lozinka"));
				powerUser.setId(resultSet.getInt("IdOsobe"));
				powerUsers.add(powerUser);
			}
			
			statementPowerUser.close();
			
			return powerUsers;
			
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
			
			PreparedStatement preparedStatementPowerUser = connection.prepareStatement("DELETE FROM power_user WHERE IdOsobe = ?;");
			preparedStatementPowerUser.setLong(1, id);
			preparedStatementPowerUser.executeUpdate();
			preparedStatementPowerUser.close();
			
			PreparedStatement preparedStatementOsoba = connection.prepareStatement("DELETE FROM osoba WHERE IdOsobe = ?;");
			preparedStatementOsoba.setLong(1, id);
			preparedStatementOsoba.executeUpdate();
			preparedStatementOsoba.close();
			
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
		PowerUser powerUser = (PowerUser) korisnik;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatementOsoba = connection.prepareStatement(
					"INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values (?,?,?,?);");
			preparedStatementOsoba.setString(1, powerUser.getIme());
			preparedStatementOsoba.setString(2, powerUser.getPrezime());
			preparedStatementOsoba.setString(3, powerUser.getKorisnickoIme());
			preparedStatementOsoba.setString(4, powerUser.getLozinkaHash());
			preparedStatementOsoba.executeUpdate();
			preparedStatementOsoba.close();
			
			PreparedStatement preparedStatementPowerUser = connection.prepareStatement("INSERT INTO power_user(IdOsobe) values (LAST_INSERT_ID());");
			preparedStatementPowerUser.executeUpdate();
			preparedStatementPowerUser.close();
			
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
			PreparedStatement preparedStatementPowerUser = connection.prepareStatement( 
					"UPDATE osoba " + 
					"SET Ime = ?, " + 
					"	 Prezime = ?, " + 
					"    KorisnickoIme = ?, " + 
					"    Lozinka = ? " +
					"WHERE IdOsobe = ?;");
			preparedStatementPowerUser.setString(1, korisnik.getIme());
			preparedStatementPowerUser.setString(2, korisnik.getPrezime());
			preparedStatementPowerUser.setString(3, korisnik.getKorisnickoIme());
			preparedStatementPowerUser.setString(4, korisnik.getLozinkaHash());
			preparedStatementPowerUser.setLong(5, korisnik.getId());
			preparedStatementPowerUser.executeUpdate();
			preparedStatementPowerUser.close();
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
													"INNER JOIN power_user PowerUser on Osoba.IdOsobe = PowerUser.IdOsobe " +
													"WHERE Osoba.KorisnickoIme = ? ");
			preparedStatementPopisivac.setString(1, korisnickoIme);
			ResultSet resultSet = preparedStatementPopisivac.executeQuery();
			
			KorisnikSistema korisnikSistema = null;
			
			if(resultSet.next()) {
				korisnikSistema = new PowerUser(resultSet.getString("Ime"),
												resultSet.getString("Prezime"),
												resultSet.getString("KorisnickoIme"),
												resultSet.getString("Lozinka"));
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
