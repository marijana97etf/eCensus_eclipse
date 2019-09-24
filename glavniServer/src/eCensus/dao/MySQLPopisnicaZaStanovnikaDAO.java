package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eCensus.baza.ConnectionPool;
import model.PopisnicaZaStanovnika;

public class MySQLPopisnicaZaStanovnikaDAO implements PopisnicaZaStanovnikaDAO {

	@Override
	public boolean skladistiPodatkeZaStanovnika(PopisnicaZaStanovnika popisnicaZaStanovnika) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatementStanovnik = connection.prepareStatement("INSERT INTO STANOVNIK(JMB,Ime,Prezime,Pol,ImeRoditelja) VALUES (?,?,?,?,?);");
			preparedStatementStanovnik.setString(1, popisnicaZaStanovnika.getJMBG());
			preparedStatementStanovnik.setString(2, popisnicaZaStanovnika.getIme());
			preparedStatementStanovnik.setString(3, popisnicaZaStanovnika.getPrezime());
			preparedStatementStanovnik.setString(4, popisnicaZaStanovnika.getPol());
			preparedStatementStanovnik.setString(5, popisnicaZaStanovnika.getImeOcaIliMajke());
			preparedStatementStanovnik.executeUpdate();
			preparedStatementStanovnik.close();
			
			PreparedStatement preparedStatementPopisnica = connection.prepareStatement("INSERT INTO POPISNICA_STANOVNIKA(JMB,IdObrasca,IdEntiteta,IdOpstine,IdPopisnogKruga,IdStana,IdDomacinstva,IdLica) VALUES (?,?,?,?,?,?,?,?);");
			preparedStatementPopisnica.setString(1, popisnicaZaStanovnika.getJMBG());
			preparedStatementPopisnica.setInt(2, popisnicaZaStanovnika.getIdObrasca());
			preparedStatementPopisnica.setInt(3, popisnicaZaStanovnika.getIdEntiteta());
			preparedStatementPopisnica.setInt(4, popisnicaZaStanovnika.getIdOpstine());
			preparedStatementPopisnica.setInt(5, popisnicaZaStanovnika.getIdPopisnogKruga());
			preparedStatementPopisnica.setInt(6, popisnicaZaStanovnika.getIdStana());
			preparedStatementPopisnica.setInt(7, popisnicaZaStanovnika.getIdDomacinstva());
			preparedStatementPopisnica.setInt(8, popisnicaZaStanovnika.getIdLica());
			preparedStatementPopisnica.executeUpdate();
			preparedStatementPopisnica.close();
			
			Statement statement = connection.createStatement();
			statement.executeQuery("SELECT LAST_INSERT_ID() AS IdPopisnice;");
			ResultSet resultSet = statement.getResultSet();
			resultSet.next();
			int idPopisnice = resultSet.getInt("IdPopisnice");
			
			PreparedStatement preparedStatementPitanjePopisnica = connection.prepareStatement("INSERT INTO PITANJE_POPISNICA_STANOVNIKA(IdPitanja,IdPopisnice) VALUES (?,?);");
			for(String pitanje : popisnicaZaStanovnika.getOdgovoriNaPitanja().keySet()) {
				preparedStatementPitanjePopisnica.setInt(1, Integer.parseInt(pitanje));
				preparedStatementPitanjePopisnica.setInt(2, idPopisnice);
				preparedStatementPitanjePopisnica.executeUpdate();
			}
			preparedStatementPitanjePopisnica.close();
			
			PreparedStatement preparedStatementOdgovorPopisnica = connection.prepareStatement("INSERT INTO POPISNICA_STANOVNIKA_ODGOVOR(IdPitanja,IdPopisnice,Odgovor) VALUES (?,?,?);");
			for(String pitanje : popisnicaZaStanovnika.getOdgovoriNaPitanja().keySet()) {
				preparedStatementOdgovorPopisnica.setInt(1, Integer.parseInt(pitanje));
				preparedStatementOdgovorPopisnica.setInt(2, idPopisnice);
				String odgovor = "";
				for(int i = 0; i < popisnicaZaStanovnika.getOdgovoriNaPitanja().get(pitanje).size(); i++) {
					odgovor += popisnicaZaStanovnika.getOdgovoriNaPitanja().get(pitanje).get(i) + ",";
				}
				preparedStatementOdgovorPopisnica.setString(3, odgovor);
				preparedStatementOdgovorPopisnica.executeUpdate();
			}
			preparedStatementOdgovorPopisnica.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		
		return false;
	}
	
}
