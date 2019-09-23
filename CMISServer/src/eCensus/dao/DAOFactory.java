package eCensus.dao;

public class DAOFactory {

	public static MySQLFactoryDAO getMySQLFactoryDAO() {
		return new MySQLFactoryDAO();
	}
	
}
