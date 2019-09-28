package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eCensus.baza.ConnectionPool;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class MySQLPopisniKrugDAO implements PopisniKrugDAO {

	@Override
	public boolean dodajPopisniKrug(PopisniKrug popisniKrug) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement("INSERT INTO popisni_krug(Opstina,Grad) VALUES (?,?);");
			preparedStatementPopisniKrug.setString(1, popisniKrug.getOpstina());
			preparedStatementPopisniKrug.setString(2, popisniKrug.getGrad());
			preparedStatementPopisniKrug.executeQuery();
			preparedStatementPopisniKrug.close();
			
			PreparedStatement preparedStatementLokalnaVarijabla = connection.prepareStatement("SET @lastID = LAST_INSERT_ID()");
			preparedStatementLokalnaVarijabla.executeUpdate();
			preparedStatementLokalnaVarijabla.close();
			
			PreparedStatement preparedStatementUlica = connection.prepareStatement("INSERT INTO ulica(IdPopisnogKruga,Naziv) VALUES (@lastID,?);");
			for(String ulica : popisniKrug.getUlice()) {
				preparedStatementUlica.setString(1, ulica);
				preparedStatementUlica.executeQuery();
			}
			preparedStatementUlica.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}

	@Override
	public boolean obrisiPopisniKrug(int idPopisnogKruga) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementUlice = connection.prepareStatement(
					"DELETE " + 
					"FROM ulica " + 
					"WHERE IdPopisnogKruga = ? AND (SELECT COUNT(*) FROM popisivac_popisni_krug WHERE IdPopisnogKruga = 2) = 0;");
			preparedStatementUlice.setInt(1, idPopisnogKruga);
			int numberRowsUlice = preparedStatementUlice.executeUpdate();
			preparedStatementUlice.close();
			
			if(numberRowsUlice == 0) {
				return false;
			}
			
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement(
					"DELETE " + 
					"FROM popisni_krug " + 
					"WHERE IdPopisnogKruga = ? AND (SELECT COUNT(*) FROM popisivac_popisni_krug WHERE IdPopisnogKruga = 2) = 0;");
			preparedStatementPopisniKrug.setInt(1, idPopisnogKruga);
			int numberRowsPopisniKrug = preparedStatementPopisniKrug.executeUpdate();
			preparedStatementPopisniKrug.close();
			
			if(numberRowsPopisniKrug == 0) {
				return false;
			}
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}
	
	@Override
	public List<PopisniKrug> getListaPopisnihKrugova(String grad, String opstina) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement statementPopisniKrugovi = connection.prepareStatement(
																					"SELECT * " + 
																					"FROM popisni_krug PopisniKrug " + 
																					"WHERE PopisniKrug.Grad = ? AND PopisniKrug.Opstina = ?;");
			statementPopisniKrugovi.setString(1, grad);
			statementPopisniKrugovi.setString(2, opstina);
			ResultSet resultSetPopisniKrugovi = statementPopisniKrugovi.executeQuery();
			
			ArrayList<PopisniKrug> popisniKrugovi = new ArrayList<>();
			while(resultSetPopisniKrugovi.next()) {
				PopisniKrug popisniKrug = new PopisniKrug();
				popisniKrug.setGrad(resultSetPopisniKrugovi.getString("Grad"));
				popisniKrug.setOpstina(resultSetPopisniKrugovi.getString("Opstina"));
				popisniKrug.setId(resultSetPopisniKrugovi.getInt("IdPopisnogKruga"));
				popisniKrugovi.add(popisniKrug);
			}
			
			if(popisniKrugovi.size() > 0) {
				PreparedStatement preparedStatementUlice = connection.prepareStatement(
																				"SELECT * " + 
																				"FROM ulica " + 
																				"WHERE IdPopisnogKruga = ?;");
				for(PopisniKrug popisniKrug : popisniKrugovi) {
					preparedStatementUlice.setInt(1, popisniKrug.getId());
					ResultSet resultSetUlice = preparedStatementUlice.executeQuery();
					ArrayList<String> ulice = new ArrayList<>();
					while(resultSetUlice.next()) {
						ulice.add(resultSetUlice.getString("Naziv"));
					}
					popisniKrug.setUlice(ulice);
				}
			
			}
			return popisniKrugovi;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

	@Override
	public PopisniKrug getPopisniKrug(int idPopisnogKruga) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement statementPopisniKrugovi = connection.prepareStatement(
																					"SELECT * " + 
																					"FROM popisni_krug PopisniKrug " + 
																					"WHERE IdPopisnogKruga = ?;");
			statementPopisniKrugovi.setInt(1, idPopisnogKruga);
			ResultSet resultSetPopisniKrugovi = statementPopisniKrugovi.executeQuery();
			
			PopisniKrug popisniKrug = null;
			while(resultSetPopisniKrugovi.next()) {
				popisniKrug = new PopisniKrug();
				popisniKrug.setGrad(resultSetPopisniKrugovi.getString("Grad"));
				popisniKrug.setOpstina(resultSetPopisniKrugovi.getString("Opstina"));
				popisniKrug.setId(resultSetPopisniKrugovi.getInt("IdPopisnogKruga"));
			}
			
			if(popisniKrug != null) {
				PreparedStatement preparedStatementUlice = connection.prepareStatement(
																				"SELECT * " + 
																				"FROM ulica " + 
																				"WHERE IdPopisnogKruga = ?;");
				preparedStatementUlice.setInt(1, popisniKrug.getId());
				ResultSet resultSetUlice = preparedStatementUlice.executeQuery();
				ArrayList<String> ulice = new ArrayList<>();
				while(resultSetUlice.next()) {
					ulice.add(resultSetUlice.getString("Naziv"));
				}
				popisniKrug.setUlice(ulice);
			}
			
			return popisniKrug;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}
	
	

}
