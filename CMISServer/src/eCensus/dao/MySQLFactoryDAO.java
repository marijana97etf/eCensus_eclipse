package eCensus.dao;

public class MySQLFactoryDAO {

	public MySQLNaloziDAO getMySQLNaloziDAO() {
		return new MySQLNaloziDAO();
	}
	
	public MySQLAktivnostDAO getMySQLAktivnostDAO() {
		return new MySQLAktivnostDAO();
	}
	
}
