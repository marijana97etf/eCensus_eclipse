package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import eCensus.baza.ConnectionPool;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class MySQLClanPKLSDAO implements ClanPKLSDAO {

	@Override
	public Collection<KorisnikSistema> getListuKorisnika() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			Statement statementClanPKLS = connection.createStatement();
			ResultSet resultSet = statementClanPKLS.executeQuery(
													"SELECT * " +
													"FROM osoba Osoba " + 
													"INNER JOIN clan_pkls ClanPKLS on Osoba.IdOsobe = ClanPKLS.IdOsobe " + 
													"INNER JOIN pkls PKLS on PKLS.IdPKLS = ClanPKLS.IdPKLS; ");
			
			ArrayList<KorisnikSistema> clanoviPKLS = new ArrayList<>();
			while(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				ClanPKLS clanPKLS = new ClanPKLS(
													resultSet.getString("Ime"),
													resultSet.getString("Prezime"),
													resultSet.getString("KorisnickoIme"),
													resultSet.getString("Lozinka"),
													JEZIK.getJEZIK(jezik),
													PISMO.getPISMO(pismo),
													resultSet.getString("Grad"),
													resultSet.getString("Opstina"));
				clanPKLS.setId(resultSet.getInt("IdOsobe"));
				clanoviPKLS.add(clanPKLS);
			}
			
			return clanoviPKLS;
			
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
			PreparedStatement preparedStatementClanPKLS = connection.prepareStatement( 
					"DELETE FROM administrator_agencije WHERE IdOsobe = ?; " + 
					"DELETE FROM administrator WHERE IdOsobe = ?; " + 
					"DELETE FROM clan_pkls WHERE IdOsobe = ?; " );
			preparedStatementClanPKLS.setLong(1, id);
			preparedStatementClanPKLS.setLong(2, id);
			preparedStatementClanPKLS.setLong(3, id);
			preparedStatementClanPKLS.executeUpdate();
			preparedStatementClanPKLS.close();
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
		ClanPKLS clanPKLS = (ClanPKLS) korisnik;
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementClanPKLS = connection.prepareStatement(
					"INSERT INTO osoba(Ime,Prezime,KorisnickoIme,Lozinka,Jezik,Pismo) values (?,?,?,?,?,?); " + 
					"INSERT INTO administrator(IdOsobe) values (LAST_INSERT_ID()); " + 
					"SET @lastID = LAST_INSERT_ID(); " + 
					"INSERT INTO pkls(Grad,Opstina) values (?,?); " + 
					"INSERT INTO clan_pkls(IdOsobe,IdPKLS) values (@lastID,LAST_INSERT_ID()); ");
			preparedStatementClanPKLS.setString(1, clanPKLS.getIme());
			preparedStatementClanPKLS.setString(2, clanPKLS.getPrezime());
			preparedStatementClanPKLS.setString(3, clanPKLS.getKorisnickoIme());
			preparedStatementClanPKLS.setString(4, clanPKLS.getLozinkaHash());
			preparedStatementClanPKLS.setString(5, clanPKLS.getJezik().toString());
			preparedStatementClanPKLS.setString(6, clanPKLS.getPismo().toString());
			preparedStatementClanPKLS.setString(7, clanPKLS.getIme());//Grad
			preparedStatementClanPKLS.setString(8, clanPKLS.getIme());//Opstina
			preparedStatementClanPKLS.executeUpdate();
			preparedStatementClanPKLS.close();
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
			PreparedStatement preparedStatementClanPKLS = connection.prepareStatement( 
					"UPDATE osoba " + 
					"SET Ime = ?, " + 
					"	 Prezime = ?, " + 
					"    KorisnickoIme = ?, " + 
					"    LozinkaHash = ?, " +
					"	 Jezik = ?, " + 
					"	 Pismo = ? " + 
					"WHERE IdOsobe = ?;");
			preparedStatementClanPKLS.setString(1, korisnik.getIme());
			preparedStatementClanPKLS.setString(2, korisnik.getPrezime());
			preparedStatementClanPKLS.setString(3, korisnik.getKorisnickoIme());
			preparedStatementClanPKLS.setString(4, korisnik.getLozinkaHash());
			preparedStatementClanPKLS.setString(5, korisnik.getJezik().toString());
			preparedStatementClanPKLS.setString(6, korisnik.getPismo().toString());
			preparedStatementClanPKLS.setLong(7, korisnik.getId());
			preparedStatementClanPKLS.executeUpdate();
			preparedStatementClanPKLS.close();
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
													"INNER JOIN clan_pkls ClanPKLS on Osoba.IdOsobe = ClanPKLS.IdOsobe " +
													"INNER JOIN pkls PKLS on PKLS.IdPKLS = ClanPKLS.IdPKLS " +
													"WHERE Osoba.KorisnickoIme = ?");
			preparedStatementPopisivac.setString(1, korisnickoIme);
			ResultSet resultSet = preparedStatementPopisivac.executeQuery();
			
			KorisnikSistema korisnikSistema = null;
			
			if(resultSet.next()) {
				String jezik = resultSet.getString("Jezik");
				String pismo = resultSet.getString("Pismo");
				korisnikSistema = new ClanPKLS(
									resultSet.getString("Ime"),
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
