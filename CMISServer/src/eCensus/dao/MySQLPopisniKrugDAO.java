package eCensus.dao;

import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import eCensus.baza.ConnectionPool;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class MySQLPopisniKrugDAO implements PopisniKrugDAO {

	@Override
	public boolean dodajPopisniKrug(PopisniKrug popisniKrug) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement("INSERT INTO popisni_krug(IdOpstine,Grad,SlikaPopisnogKruga) VALUES (?,?,?);");
			preparedStatementPopisniKrug.setInt(1, popisniKrug.getIdOpstine());
			preparedStatementPopisniKrug.setString(2, popisniKrug.getGrad());
			if(popisniKrug.getSlikaBytes() != null) {
				preparedStatementPopisniKrug.setBlob(3, new ByteArrayInputStream(popisniKrug.getSlikaBytes()), popisniKrug.getSlikaBytes().length);
			} else {
				preparedStatementPopisniKrug.setBlob(3, connection.createBlob());
			}
			preparedStatementPopisniKrug.executeUpdate();
			preparedStatementPopisniKrug.close();
			
			PreparedStatement preparedStatementLokalnaVarijabla = connection.prepareStatement("SET @lastID = LAST_INSERT_ID();");
			preparedStatementLokalnaVarijabla.executeUpdate();
			preparedStatementLokalnaVarijabla.close();
			
			PreparedStatement preparedStatementUlica = connection.prepareStatement("INSERT INTO ulica(IdPopisnogKruga,IdOpstine,Naziv) VALUES (@lastID,?,?);");
			for(String ulica : popisniKrug.getUlice()) {
				preparedStatementUlica.setInt(1, popisniKrug.getIdOpstine());
				preparedStatementUlica.setString(2, ulica);
				preparedStatementUlica.executeUpdate();
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
	public boolean obrisiPopisniKrug(int idPopisnogKruga, int idOpstine) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementUlice = connection.prepareStatement(
					"DELETE " + 
					"FROM ulica " + 
					"WHERE IdPopisnogKruga = ? AND IdOpstine = ? AND (SELECT COUNT(*) FROM popisivac_popisni_krug WHERE IdPopisnogKruga = ? AND IdOpstine = ?) = 0;");
			preparedStatementUlice.setInt(1, idPopisnogKruga);
			preparedStatementUlice.setInt(2, idOpstine);
			preparedStatementUlice.setInt(3, idPopisnogKruga);
			preparedStatementUlice.setInt(4, idOpstine);
			int numberRowsUlice = preparedStatementUlice.executeUpdate();
			preparedStatementUlice.close();
			
			if(numberRowsUlice == 0) {
				return false;
			}
			
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement(
					"DELETE " + 
					"FROM popisni_krug " + 
					"WHERE IdPopisnogKruga = ? AND IdOpstine = ? AND (SELECT COUNT(*) FROM popisivac_popisni_krug WHERE IdPopisnogKruga = ? AND IdOpstine = ?) = 0;");
			preparedStatementPopisniKrug.setInt(1, idPopisnogKruga);
			preparedStatementPopisniKrug.setInt(2, idOpstine);
			preparedStatementPopisniKrug.setInt(3, idPopisnogKruga);
			preparedStatementPopisniKrug.setInt(4, idOpstine);
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
	public List<PopisniKrug> getListaPopisnihKrugova(String grad, int idOpstine) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement statementPopisniKrugovi = connection.prepareStatement(
																					"SELECT * " + 
																					"FROM popisni_krug PopisniKrug " + 
																					"WHERE PopisniKrug.Grad = ? AND PopisniKrug.IdOpstine = ?;");
			statementPopisniKrugovi.setString(1, grad);
			statementPopisniKrugovi.setInt(2, idOpstine);
			ResultSet resultSetPopisniKrugovi = statementPopisniKrugovi.executeQuery();
			
			ArrayList<PopisniKrug> popisniKrugovi = new ArrayList<>();
			while(resultSetPopisniKrugovi.next()) {
				PopisniKrug popisniKrug = new PopisniKrug();
				popisniKrug.setGrad(resultSetPopisniKrugovi.getString("Grad"));
				popisniKrug.setIdOpstine(resultSetPopisniKrugovi.getInt("IdOpstine"));
				popisniKrug.setId(resultSetPopisniKrugovi.getInt("IdPopisnogKruga"));
				Blob slikaPopisnogKruga = resultSetPopisniKrugovi.getBlob("SlikaPopisnogKruga");
				popisniKrug.setSlikaBytes(slikaPopisnogKruga.getBytes(1l, (int) slikaPopisnogKruga.length()));
				popisniKrugovi.add(popisniKrug);
			}
			
			if(popisniKrugovi.size() > 0) {
				PreparedStatement preparedStatementUlice = connection.prepareStatement(
																				"SELECT * " + 
																				"FROM ulica " + 
																				"WHERE IdPopisnogKruga = ? AND IdOpstine = ?;");
				for(PopisniKrug popisniKrug : popisniKrugovi) {
					preparedStatementUlice.setInt(1, popisniKrug.getId());
					preparedStatementUlice.setInt(2, popisniKrug.getIdOpstine());
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
	public PopisniKrug getPopisniKrug(int idPopisnogKruga, int idOpstine) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement statementPopisniKrugovi = connection.prepareStatement(
																					"SELECT * " + 
																					"FROM popisni_krug PopisniKrug " + 
																					"WHERE IdPopisnogKruga = ? AND IdOpstine = ?;");
			statementPopisniKrugovi.setInt(1, idPopisnogKruga);
			statementPopisniKrugovi.setInt(2, idOpstine);
			ResultSet resultSetPopisniKrugovi = statementPopisniKrugovi.executeQuery();
			
			PopisniKrug popisniKrug = null;
			while(resultSetPopisniKrugovi.next()) {
				popisniKrug = new PopisniKrug();
				popisniKrug.setGrad(resultSetPopisniKrugovi.getString("Grad"));
				popisniKrug.setIdOpstine(resultSetPopisniKrugovi.getInt("IdOpstine"));
				popisniKrug.setId(resultSetPopisniKrugovi.getInt("IdPopisnogKruga"));
				Blob slikaPopisnogKruga = resultSetPopisniKrugovi.getBlob("SlikaPopisnogKruga");
				popisniKrug.setSlikaBytes(slikaPopisnogKruga.getBytes(1l, (int) slikaPopisnogKruga.length()));
			}
			
			if(popisniKrug != null) {
				PreparedStatement preparedStatementUlice = connection.prepareStatement(
																				"SELECT * " + 
																				"FROM ulica " + 
																				"WHERE IdPopisnogKruga = ? AND IdOpstine = ?;");
				preparedStatementUlice.setInt(1, popisniKrug.getId());
				preparedStatementUlice.setInt(2, popisniKrug.getIdOpstine());
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
