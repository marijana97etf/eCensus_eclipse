package eCensus.dao;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import eCensus.baza.ConnectionPool;
import model.pracenje_popisa.izvjestaji_o_popisivacu.Kontrolnik;

public class MySQLKontrolnikDAO implements KontrolnikDAO {

	@Override
	public boolean azurirajKontrolnik(Kontrolnik kontrolnik) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			PreparedStatement preparedStatementPopisniKrug = connection.prepareStatement(
					"INSERT INTO kontrolnik(IdPopisnogKruga,IdOpstine,BrojPopisanihStanova, " + 
					"						BrojPopisanihDomacinstava,BrojClanovaDomacinstava) VALUES (?,?,?,?,?) " + 
					"ON DUPLICATE KEY UPDATE " + 
					"BrojPopisanihStanova = BrojPopisanihStanova + VALUES(BrojPopisanihStanova), " + 
					"BrojPopisanihDomacinstava = BrojPopisanihDomacinstava + VALUES(BrojPopisanihDomacinstava), " + 
					"BrojClanovaDomacinstava = BrojClanovaDomacinstava + VALUES(BrojClanovaDomacinstava);");
			preparedStatementPopisniKrug.setInt(1, kontrolnik.getIdPopisnogKruga());
			preparedStatementPopisniKrug.setInt(2, kontrolnik.getIdOpstine());
			preparedStatementPopisniKrug.setInt(3, kontrolnik.getBrojPopisanihStanova());
			preparedStatementPopisniKrug.setInt(4, kontrolnik.getBrojPopisanihDomacinstava());
			preparedStatementPopisniKrug.setInt(5, kontrolnik.getBrojClanovaDomacinstava());
			
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

}
