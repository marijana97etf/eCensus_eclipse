package eCensus.dao;

public class MySQLFactoryDAO extends DAOFactory {

	public PopisnicaZaDomacinstvoDAO getPopisnicaZaDomacinstvoDAO() {
		return new MySQLPopisnicaZaDomacinstvoDAO();
	}
	
	public PopisnicaZaStanovnikaDAO getPopisnicaZaStanovnikaDAO() {
		return new MySQLPopisnicaZaStanovnikaDAO();
	}
	
	public MySQLStatistickiPodaciDAO getMySQLStatistickiPodaciDAO() {
		return new MySQLStatistickiPodaciDAO();
	}
	
}
