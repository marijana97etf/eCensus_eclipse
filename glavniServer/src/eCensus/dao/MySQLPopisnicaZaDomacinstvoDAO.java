package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import eCensus.baza.ConnectionPool;
import model.ClanDomacinstva;
import model.PopisnicaZaDomacinstvo;

public class MySQLPopisnicaZaDomacinstvoDAO implements PopisnicaZaDomacinstvoDAO {

	
	@Override
	public boolean skladistiPodatkeZaDomacinstvo(PopisnicaZaDomacinstvo popisnicaZaDomacinstvo) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatementStan = connection.prepareStatement("INSERT INTO STAN_DOMACINSTVO(Ulica,KucniBroj,Dodatak,Ulaz,BrojStana,IdBroj) VALUES (?,?,?,?,?,?);");
			preparedStatementStan.setString(1, popisnicaZaDomacinstvo.getUlica());
			preparedStatementStan.setInt(2, Integer.parseInt(popisnicaZaDomacinstvo.getKucniBroj()));
			preparedStatementStan.setInt(3, Integer.parseInt(popisnicaZaDomacinstvo.getDodatak()));
			preparedStatementStan.setInt(4, Integer.parseInt(popisnicaZaDomacinstvo.getUlaz()));
			preparedStatementStan.setInt(5, Integer.parseInt(popisnicaZaDomacinstvo.getBrojStana()));
			preparedStatementStan.setInt(6, Integer.parseInt(popisnicaZaDomacinstvo.getIdBroj()));
			preparedStatementStan.executeUpdate();
			
			Statement statement = connection.createStatement();
			statement.executeQuery("SELECT LAST_INSERT_ID() AS IdStanaDomacinstva;");
			ResultSet resultSet = statement.getResultSet();
			resultSet.next();
			int idStanaDomacinstva = resultSet.getInt("IdStanaDomacinstva");
			statement.close();
			
			preparedStatementStan.close();
			
			PreparedStatement preparedStatementDomacinstvo = connection.prepareStatement("INSERT INTO POPISNICA_DOMACINSTVA(IdStanaDomacinstva,IdObrasca,IdEntiteta,IdOpstine,IdPopisnogKruga,IdStana,IdDomacinstva,IdZgrade) VALUES (?,?,?,?,?,?,?,?);");
			preparedStatementDomacinstvo.setInt(1, idStanaDomacinstva);
			preparedStatementDomacinstvo.setInt(2, popisnicaZaDomacinstvo.getIdObrasca());
			preparedStatementDomacinstvo.setInt(3, popisnicaZaDomacinstvo.getIdEntiteta());
			preparedStatementDomacinstvo.setInt(4, popisnicaZaDomacinstvo.getIdOpstine());
			preparedStatementDomacinstvo.setInt(5, popisnicaZaDomacinstvo.getIdPopisnogKruga());
			preparedStatementDomacinstvo.setInt(6, popisnicaZaDomacinstvo.getIdStana());
			preparedStatementDomacinstvo.setInt(7, popisnicaZaDomacinstvo.getIdDomacinstva());
			preparedStatementDomacinstvo.setInt(8, popisnicaZaDomacinstvo.getIdZgrade());
			preparedStatementDomacinstvo.executeUpdate();
			preparedStatementDomacinstvo.close();
			
			Statement statement2 = connection.createStatement();
			statement2.executeQuery("SELECT LAST_INSERT_ID() AS IdPopisnice;");
			ResultSet resultSet2 = statement2.getResultSet();
			resultSet2.next();
			int idPopisnice = resultSet2.getInt("IdPopisnice");
			statement2.close();
			
			//DODATI CLAN_DOMACINSTVO
			//POTREBNO UCITATI NOVE KLASE U LIB
			PreparedStatement preparedStatementClanDomacinstva = connection.prepareStatement("INSERT INTO CLAN_DOMACINSTVA(IdPopisnice,Ime,Prezime,ImeRoditelja,ImePartnera,DaLiJeClanDomacinstva,DaLiJePrisutan,OdnosPremaNosiocuDomacinstva,SifraZaOdnosPremaNosiocuDomacinstva,RedniBrojPorodice,PolozajClanaUPorodici,RazlogOdsustvaIliPrisustva) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
			for(ClanDomacinstva clanDomacinstva : popisnicaZaDomacinstvo.getSpisakLica()) {
				preparedStatementClanDomacinstva.setInt(1, idPopisnice);
				preparedStatementClanDomacinstva.setString(2, clanDomacinstva.getIme());
				preparedStatementClanDomacinstva.setString(3, clanDomacinstva.getPrezime());
				preparedStatementClanDomacinstva.setString(4, "");//Ime roditelja
				preparedStatementClanDomacinstva.setString(5, "");//Ime partnera
				preparedStatementClanDomacinstva.setBoolean(6, false); //Da li je clan domacinstva
				preparedStatementClanDomacinstva.setBoolean(7, false); //Da li je prisutan
				preparedStatementClanDomacinstva.setString(8, clanDomacinstva.getOdnosPremaNosiocuDomacinstva());
				preparedStatementClanDomacinstva.setInt(9, 1); //Sifra za odnos
				preparedStatementClanDomacinstva.setInt(10, clanDomacinstva.getRedniBrojPorodice());
				preparedStatementClanDomacinstva.setInt(11, /*clanDomacinstva.getPolozajClanaUPorodici()*/ 0);// polozaj clana u porodici
				preparedStatementClanDomacinstva.setInt(12, 0);//Razlog prisustva ili odsustva
				preparedStatementClanDomacinstva.executeUpdate();
			}
			preparedStatementClanDomacinstva.close();
			
			
			PreparedStatement preparedStatementPitanjePopisnica = connection.prepareStatement("INSERT INTO PITANJE_POPISNICA_DOMACINSTVO(IdPitanja,IdPopisnice) VALUES (?,?);");
			for(String pitanje : popisnicaZaDomacinstvo.getOdgovoriNaPitanja().keySet()) {
				preparedStatementPitanjePopisnica.setInt(1, Integer.parseInt(pitanje));
				preparedStatementPitanjePopisnica.setInt(2, idPopisnice);
				preparedStatementPitanjePopisnica.executeUpdate();
			}
			preparedStatementPitanjePopisnica.close();
			
			PreparedStatement preparedStatementOdgovorPopisnica = connection.prepareStatement("INSERT INTO POPISNICA_DOMACINSTVO_ODGOVOR(IdPitanja,IdPopisnice,Odgovor) VALUES (?,?,?);");
			for(String pitanje : popisnicaZaDomacinstvo.getOdgovoriNaPitanja().keySet()) {
				preparedStatementOdgovorPopisnica.setInt(1, Integer.parseInt(pitanje));
				preparedStatementOdgovorPopisnica.setInt(2, idPopisnice);
				String odgovor = "";
				for(int i = 0; i < popisnicaZaDomacinstvo.getOdgovoriNaPitanja().get(pitanje).size(); i++) {
					odgovor += popisnicaZaDomacinstvo.getOdgovoriNaPitanja().get(pitanje).get(i) + ",";
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
