package eCensus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import eCensus.baza.ConnectionPool;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;

public class MySQLAktivnostDAO implements AktivnostDAO {

	@Override
	public boolean azurirajAktivnost(int idPopisivaca, DnevnaAktivnost dnevnaAktivnost) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatementAktivnost = connection.prepareCall(
					"INSERT INTO aktivnost(Datum,IdOsobe,BrojPopisnicaStanovnika,BrojPopisnicaDomacinstva) VALUES (?,?,?,?) " + 
					"ON DUPLICATE KEY UPDATE " + 
					"BrojPopisnicaStanovnika = BrojPopisnicaStanovnika + VALUES(BrojPopisnicaStanovnika), " + 
					"BrojPopisnicaDomacinstva = BrojPopisnicaDomacinstva + VALUES(BrojPopisnicaDomacinstva);");
			preparedStatementAktivnost.setDate(1, Date.valueOf(dnevnaAktivnost.getDan()));
			preparedStatementAktivnost.setInt(2, idPopisivaca);
			preparedStatementAktivnost.setInt(3, dnevnaAktivnost.getBrojPopisanihStanovnika());
			preparedStatementAktivnost.setInt(4, dnevnaAktivnost.getBrojPopisanihDomacinstava());
			preparedStatementAktivnost.executeUpdate();
			preparedStatementAktivnost.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return false;
	}

	@Override
	public List<DnevnaAktivnost> getListaDnevnihAktivnosti(int idPopisivaca) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatementAktivnost = connection.prepareCall(
					"SELECT * " + 
					"FROM aktivnost Aktivnost " + 
					"WHERE IdOsobe = ?;");
			preparedStatementAktivnost.setInt(1, idPopisivaca);
			ResultSet resultSet = preparedStatementAktivnost.executeQuery();
			ArrayList<DnevnaAktivnost> dnevneAktivnosti = new ArrayList<>();
			while(resultSet.next()) {
				DnevnaAktivnost dnevnaAktivnost = new DnevnaAktivnost();
				dnevnaAktivnost.setDan(new Date(resultSet.getDate("Datum").getTime()).toLocalDate());
				dnevnaAktivnost.setBrojPopisanihStanovnika(resultSet.getInt("BrojPopisnicaStanovnika"));
				dnevnaAktivnost.setBrojPopisanihDomacinstava(resultSet.getInt("BrojPopisnicaDomacinstva"));
				dnevneAktivnosti.add(dnevnaAktivnost);
			}
			preparedStatementAktivnost.close();
			
			return dnevneAktivnosti;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return null;
	}

}
