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
				Popisivac popisivac = new Popisivac(resultSet.getString("Ime"),
													resultSet.getString("Prezime"),
													resultSet.getString("KorisnickoIme"),
													resultSet.getString("Lozinka"));
				popisivac.setId(resultSet.getInt("IdOsobe"));
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
			
			PreparedStatement preparedStatementPopisivac = connection.prepareStatement("DELETE FROM popisivac WHERE IdOsobe = ?;");
			preparedStatementPopisivac.setLong(1, id);
			preparedStatementPopisivac.executeUpdate();
			preparedStatementPopisivac.close();
			
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
		Popisivac popisivac = (Popisivac) korisnik;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementOsoba = connection.prepareStatement(
					"INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka) values (?,?,?,?);");
			preparedStatementOsoba.setString(1, popisivac.getIme());
			preparedStatementOsoba.setString(2, popisivac.getPrezime());
			preparedStatementOsoba.setString(3, popisivac.getKorisnickoIme());
			preparedStatementOsoba.setString(4, popisivac.getLozinkaHash());
			preparedStatementOsoba.executeUpdate();
			preparedStatementOsoba.close();
			
			PreparedStatement preparedStatementPopisivac = connection.prepareStatement("INSERT INTO popisivac(IdOsobe) values (LAST_INSERT_ID());");
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
					"    Lozinka = ? " +
					"WHERE IdOsobe = ?;");
			preparedStatementPopisivac.setString(1, korisnik.getIme());
			preparedStatementPopisivac.setString(2, korisnik.getPrezime());
			preparedStatementPopisivac.setString(3, korisnik.getKorisnickoIme());
			preparedStatementPopisivac.setString(4, korisnik.getLozinkaHash());
			preparedStatementPopisivac.setLong(5, korisnik.getId());
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
				korisnikSistema = new Popisivac(resultSet.getString("Ime"),
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
