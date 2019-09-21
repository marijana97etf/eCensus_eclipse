package eCensus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import eCensus.baza.ConnectionPool;

public class MySQLStatistickiPodaciDAO {

	public int getBrojStanovnikaPremaPojedinacnimGodinamaStarostiIPolu(String idEntiteta, String idOpstina, String starost, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND YEAR(CURDATE()) - CONVERT(CONCAT(if(SUBSTRING(stanovnik.JMB,4,1) >= \"8\",\"1\",\"2\"),SUBSTRING(stanovnik.JMB,5,3)), UNSIGNED INTEGER) like ?;");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstina);
			preparedStatement.setString(3, pol);
			preparedStatement.setString(4, starost);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaPremaBracnomStatusuIPolu(String idEntiteta, String idOpstina, String status, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 21" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND odgovorStanovnika.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstina);
			preparedStatement.setString(3, pol);
			preparedStatement.setString(4, status);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojZenskogStanovnistvaPremaBrojuZivorodjeneDjece(String idEntiteta,String idOpstina, String brojZivorodjeneDjece) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 23" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol = \"Zenski\" AND CONVERT(odgovorStanovnika.Odgovor, UNSIGNED INTEGER) like ?;");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstina);
			preparedStatement.setString(3, brojZivorodjeneDjece);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojZenskogStanovnistva = resultSet.getInt("Broj");
			
			return brojZenskogStanovnistva;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaNacionalnojPripadnosti(String idEntiteta, String idOpstine, String nacionalnaPripadnost, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					"FROM STANOVNIK stanovnik" + 
					"INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					"INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 25" + 
					"WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND odgovorStanovnika.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, pol);
			preparedStatement.setString(4, nacionalnaPripadnost);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovnikPremaVjeroispovjesti(String idEntiteta, String idOpstine, String vjeroispovjest, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 26" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND odgovorStanovnika.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, pol);
			preparedStatement.setString(4, vjeroispovjest);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaMaternjemJeziku(String idEntiteta, String idOpstine, String maternjiJezik, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 27" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND odgovorStanovnika.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, pol);
			preparedStatement.setString(4, maternjiJezik);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaPismenosti(String idEntiteta, String idOpstine, String pismenost, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 31" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND odgovorStanovnika.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, pol);
			// pismenost treba biti oblika "Da" ili "Ne"
			preparedStatement.setString(4, pismenost);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaZavrsenojSkoli(String idEntiteta, String idOpstine, String zavrsenaSkola, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 29" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND odgovorStanovnika.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, pol);
			preparedStatement.setString(4, zavrsenaSkola);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaKompjuterskojPismenosti(String idEntiteta, String idOpstine, String kompjuterskaPismenost, String pol) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STANOVNIK stanovnik" + 
					" INNER JOIN popisnica_stanovnika popisnicaStanovnika on popisnicaStanovnika.JMB = stanovnik.JMB" + 
					" INNER JOIN popisnica_stanovnika_odgovor odgovorStanovnika on odgovorStanovnika.IdPopisnice = popisnicaStanovnika.IdPopisnice AND odgovorStanovnika.IdPitanja = 28" + 
					" WHERE popisnicaStanovnika.IdEntiteta like ? AND popisnicaStanovnika.IdOpstine like ? AND Pol like ? AND odgovorStanovnika.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, pol);
			// Ako je stanovnik kompjuterski nepismen onda treba poslati "Ne"
			preparedStatement.setString(4, kompjuterskaPismenost);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanovnika = resultSet.getInt("Broj");
			
			return brojStanovnika;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	
	//Domacinstvo
	
	public int getBrojDomacinstavaPremaBrojuClanova(String idEntiteta, String idOpstine, String brojClanova) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STAN_DOMACINSTVO stanDomacinstvo" + 
					" INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva" + 
					" WHERE popisnicaDomacinstva.IdEntiteta like ? AND popisnicaDomacinstva.IdOpstine like ? AND (" + 
					"	SELECT COUNT(*)" + 
					"   FROM clan_domacinstva clanDomacinstva" + 
					"	WHERE clanDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice" + 
					"    ) like ?;");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, brojClanova);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojDomacinstava = resultSet.getInt("Broj");
			
			return brojDomacinstava;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojDomacinstavaPremaPoljoprivrednojAktivnosti(String idEntiteta, String idOpstine, String poljoprivreda, String prodaja) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STAN_DOMACINSTVO stanDomacinstvo" + 
					" INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva" + 
					" INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva on odgovorDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva.IdPitanja = 30" + 
					" INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva1 on odgovorDomacinstva1.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva1.IdPitanja = 32" + 
					" WHERE popisnicaDomacinstva.IdEntiteta like ? AND popisnicaDomacinstva.IdOpstine like ? AND odgovorDomacinstva.Odgovor like CONCAT(\"%\",?,\"%\") AND odgovorDomacinstva1.Odgovor like CONCAT(\"%\",?,\"%\");");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, poljoprivreda);
			preparedStatement.setString(4, prodaja);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojDomacinstava = resultSet.getInt("Broj");
			
			return brojDomacinstava;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	//Zgrade broj stanova
	public int getBrojZgradaPremaBrojuStanova(String idEntiteta, String idOpstine, String brojStanova) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(DISTINCT(popisnicaDomacinstva.IdZgrade)) AS Broj" + 
					" FROM STAN_DOMACINSTVO stanDomacinstvo" + 
					" INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva" + 
					" WHERE popisnicaDomacinstva.IdEntiteta like ? AND popisnicaDomacinstva.IdOpstine like ? AND (" + 
					"    SELECT COUNT(*) AS Broj" + 
					"	 FROM STAN_DOMACINSTVO stanDomacinstvo1" + 
					"	 INNER JOIN popisnica_domacinstva popisnicaDomacinstva1 on stanDomacinstvo1.IdStanaDomacinstva = popisnicaDomacinstva1.IdStanaDomacinstva" + 
					"	 WHERE popisnicaDomacinstva1.IdEntiteta = popisnicaDomacinstva.IdEntiteta" + 
					"      AND popisnicaDomacinstva1.IdOpstine = popisnicaDomacinstva.IdOpstine" + 
					"      AND popisnicaDomacinstva1.IdPopisnogKruga = popisnicaDomacinstva.IdPopisnogKruga" + 
					"      AND popisnicaDomacinstva1.IdZgrade = popisnicaDomacinstva.IdZgrade" + 
					"    ) like ?;");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, brojStanova);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojZgrada = resultSet.getInt("Broj");
			
			return brojZgrada;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getBrojStanovaPremaBrojuSoba(String idEntiteta, String idOpstine, String brojSoba) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT COUNT(*) AS Broj" + 
					" FROM STAN_DOMACINSTVO stanDomacinstvo" + 
					" INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva" + 
					" INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva on odgovorDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva.IdPitanja = 7" + 
					" WHERE popisnicaDomacinstva.IdEntiteta like ? AND popisnicaDomacinstva.IdOpstine like ? AND CONVERT(SUBSTRING(odgovorDomacinstva.Odgovor,1,length(odgovorDomacinstva.Odgovor) - 1), UNSIGNED INTEGER) like ?;");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, brojSoba);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int brojStanova = resultSet.getInt("Broj");
			
			return brojStanova;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
	public int getPovrsinaStanovaPremaBrojuSoba(String idEntiteta, String idOpstine, String brojSoba) {
		Connection connection = null;
		try {
			connection = ConnectionPool.getInstance().checkOut();
			
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT SUM(CONVERT(SUBSTRING(odgovorDomacinstva.Odgovor,1,length(odgovorDomacinstva.Odgovor) - 1), UNSIGNED INTEGER)) AS UkupnaPovrsina" + 
					" FROM STAN_DOMACINSTVO stanDomacinstvo" + 
					" INNER JOIN popisnica_domacinstva popisnicaDomacinstva on stanDomacinstvo.IdStanaDomacinstva = popisnicaDomacinstva.IdStanaDomacinstva" + 
					" INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva on odgovorDomacinstva.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva.IdPitanja = 6" + 
					" INNER JOIN popisnica_domacinstvo_odgovor odgovorDomacinstva1 on odgovorDomacinstva1.IdPopisnice = popisnicaDomacinstva.IdPopisnice AND odgovorDomacinstva1.IdPitanja = 7" + 
					" WHERE popisnicaDomacinstva.IdEntiteta like ? AND popisnicaDomacinstva.IdOpstine like ? AND CONVERT(SUBSTRING(odgovorDomacinstva1.Odgovor,1,length(odgovorDomacinstva.Odgovor) - 1), UNSIGNED INTEGER) like ?;");
			preparedStatement.setString(1, idEntiteta);
			preparedStatement.setString(2, idOpstine);
			preparedStatement.setString(3, brojSoba);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			int ukupnaPovrsina = resultSet.getInt("UkupnaPovrsina");
			
			return ukupnaPovrsina;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(connection);
		}
		return -1;
	}
	
}
