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
					"						BrojPopisanihDomacinstava,BrojDomacinstavaKojiSeBavePoljoprivredom, " + 
					"                       BrojPrisutnihClanovaDomacinstva,BrojOdsutnihClanovaDomacinstva, " + 
					"                       BrojNeodazvanihLica,BrojNeodazvanihDomacinstava) VALUES (?,?,?,?,?,?,?,?,?) " + 
					"ON DUPLICATE KEY UPDATE " + 
					"BrojPopisanihStanova = BrojPopisanihStanova + VALUES(BrojPopisanihStanova), " + 
					"BrojPopisanihDomacinstava = BrojPopisanihDomacinstava + VALUES(BrojPopisanihDomacinstava), " + 
					"BrojDomacinstavaKojiSeBavePoljoprivredom = BrojDomacinstavaKojiSeBavePoljoprivredom + VALUES(BrojDomacinstavaKojiSeBavePoljoprivredom), " + 
					"BrojPrisutnihClanovaDomacinstva = BrojPrisutnihClanovaDomacinstva + VALUES(BrojPrisutnihClanovaDomacinstva), " + 
					"BrojOdsutnihClanovaDomacinstva = BrojOdsutnihClanovaDomacinstva + VALUES(BrojOdsutnihClanovaDomacinstva), " + 
					"BrojNeodazvanihLica = BrojNeodazvanihLica + VALUES(BrojNeodazvanihLica), " + 
					"BrojNeodazvanihDomacinstava = BrojNeodazvanihDomacinstava + VALUES(BrojNeodazvanihDomacinstava);");
			preparedStatementPopisniKrug.setInt(1, kontrolnik.getIdPopisnogKruga());
			preparedStatementPopisniKrug.setInt(2, kontrolnik.getIdOpstine());
			preparedStatementPopisniKrug.setInt(3, kontrolnik.getBrojPopisanihStanova());
			preparedStatementPopisniKrug.setInt(4, kontrolnik.getBrojPopisanihDomacinstava());
			preparedStatementPopisniKrug.setInt(5, kontrolnik.getBrojDomacinstavaKojiSeBavePoljoprivredom());
			preparedStatementPopisniKrug.setInt(6, kontrolnik.getBrojPrisutnihClanovaDomacinstva());
			preparedStatementPopisniKrug.setInt(7, kontrolnik.getBrojOdsutnihClanovaDomacinstva());
			preparedStatementPopisniKrug.setInt(8, kontrolnik.getBrojNeodazvanihLica());
			preparedStatementPopisniKrug.setInt(9, kontrolnik.getBrojNeodazvanihDomacinstava());
			
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
