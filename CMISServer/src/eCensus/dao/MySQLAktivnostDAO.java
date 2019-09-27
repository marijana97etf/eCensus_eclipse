package eCensus.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
