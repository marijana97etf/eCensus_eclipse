package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eCensus.baza.ConnectionPool;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

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
			
			PreparedStatement preparedStatementPopisniKrugPopisivaca = connection.prepareStatement("DELETE FROM popisivac_popisni_krug WHERE IdOsobe = ?;");
			preparedStatementPopisniKrugPopisivaca.setLong(1, id);
			preparedStatementPopisniKrugPopisivaca.executeUpdate();
			preparedStatementPopisniKrugPopisivaca.close();
			
			PreparedStatement preparedStatementOcjena = connection.prepareStatement("DELETE FROM ocjena WHERE IdOsobe_POPISIVAC = ?;");
			preparedStatementOcjena.setLong(1, id);
			preparedStatementOcjena.executeUpdate();
			preparedStatementOcjena.close();
			
			PreparedStatement preparedStatementAktivnost = connection.prepareStatement("DELETE FROM aktivnost WHERE IdOsobe = ?;");
			preparedStatementAktivnost.setLong(1, id);
			preparedStatementAktivnost.executeUpdate();
			preparedStatementAktivnost.close();
			
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
				Popisivac popisivac = new Popisivac(resultSet.getString("Ime"),
												resultSet.getString("Prezime"),
												resultSet.getString("KorisnickoIme"),
												resultSet.getString("Lozinka"));
				popisivac.setId(resultSet.getInt("IdOsobe"));
				
				List<PopisniKrug> popisniKrugovi = getListaPopisnihKrugovaPopisivaca((int) popisivac.getId());
				if(popisniKrugovi.size() > 0) {
					for(PopisniKrug popisniKrug : popisniKrugovi) {
						popisivac.dodajPopisniKrug(popisniKrug);
					}
				}
				
				korisnikSistema = popisivac;
				
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
	public int getOcjena(int idPopisivaca) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementOcjena = connection.prepareStatement(
													"SELECT Ocjena " + 
													"FROM ocjena " + 
													"WHERE idOsobe_POPISIVAC = ?;");
			preparedStatementOcjena.setInt(1, idPopisivaca);
			ResultSet resultSet = preparedStatementOcjena.executeQuery();
			resultSet.next();
			
			int ocjena = resultSet.getInt("Ocjena");
			
			preparedStatementOcjena.close();
			
			return ocjena;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return 0;
	}
	
	@Override
	public boolean azurirajOcjenu(int idPopisivaca, int idOGInstruktora, int ocjena) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementOcjena = connection.prepareStatement(
													"INSERT INTO ocjena(IdOsobe_POPISIVAC,IdOsobe_OG_INSTRUKTOR,Ocjena) VALUES (?,?,?) " + 
													"ON DUPLICATE KEY UPDATE " + 
													"Ocjena = VALUES(Ocjena); ");
			preparedStatementOcjena.setInt(1, idPopisivaca);
			preparedStatementOcjena.setInt(2, idOGInstruktora);
			preparedStatementOcjena.setInt(3, ocjena);
			preparedStatementOcjena.executeUpdate();
			preparedStatementOcjena.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}

	@Override
	public boolean dodajPopisniKrug(int idPopisivaca, int idPopisnogKruga, int idOpstine) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement("INSERT INTO popisivac_popisni_krug(IdOsobe,IdPopisnogKruga,IdOpstine) VALUES (?,?,?);");
			preparedStatementPopisniKrug.setInt(1, idPopisivaca);
			preparedStatementPopisniKrug.setInt(2, idPopisnogKruga);
			preparedStatementPopisniKrug.setInt(3, idOpstine);
			preparedStatementPopisniKrug.executeUpdate();
			preparedStatementPopisniKrug.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}

	@Override
	public boolean obrisiPopisniKrug(int idPopisivaca, int idPopisnogKruga, int idOpstine) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement("DELETE " + 
																						 "FROM popisivac_popisni_krug " + 
																						 "WHERE IdOsobe = ? AND IdPopisnogKruga = ? AND IdOpstine = ?;");
			preparedStatementPopisniKrug.setInt(1, idPopisivaca);
			preparedStatementPopisniKrug.setInt(2, idPopisnogKruga);
			preparedStatementPopisniKrug.setInt(3, idOpstine);
			preparedStatementPopisniKrug.executeUpdate();
			preparedStatementPopisniKrug.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}

	@Override
	public List<PopisniKrug> getListaPopisnihKrugovaPopisivaca(int idPopisivaca) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement(
					"SELECT * " + 
					"FROM popisivac Popisivac " + 
					"INNER JOIN popisivac_popisni_krug PopisivacPopisniKrug on popisivac.IdOsobe = PopisivacPopisniKrug.IdOsobe " +
					"WHERE Popisivac.IdOsobe = ?");
			preparedStatementPopisniKrug.setInt(1, idPopisivaca);
			ResultSet resultSetPopisniKrugoviPopisivaca = preparedStatementPopisniKrug.executeQuery();
			
			ArrayList<PopisniKrug> popisniKrugovi = new ArrayList<>();
			MySQLPopisniKrugDAO popisniKrugDAO = DAOFactory.getMySQLFactoryDAO().getMySQLPopisniKrugDAO();
			while(resultSetPopisniKrugoviPopisivaca.next()) {
				PopisniKrug popisniKrug = popisniKrugDAO.getPopisniKrug(resultSetPopisniKrugoviPopisivaca.getInt("IdPopisnogKruga"), resultSetPopisniKrugoviPopisivaca.getInt("IdOpstine"));
				popisniKrugovi.add(popisniKrug);
			}
			preparedStatementPopisniKrug.close();
			
			return popisniKrugovi;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

}
