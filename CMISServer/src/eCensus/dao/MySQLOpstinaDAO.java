package eCensus.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import eCensus.baza.ConnectionPool;

public class MySQLOpstinaDAO implements OpstinaDAO {

	@Override
	public Map<String, String> getMapaOpstina() {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			Statement statementOpstina = connection.createStatement();
			ResultSet resultSet = statementOpstina.executeQuery("SELECT * FROM opstina;");
			
			Map<String,String> opstine = new HashMap<String,String>();
			
			while(resultSet.next()) {
				opstine.put(resultSet.getString("IdOpstine"), resultSet.getString("Naziv"));
			}
			
			return opstine;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

	
	
}
