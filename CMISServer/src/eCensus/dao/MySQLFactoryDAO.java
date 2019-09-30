package eCensus.dao;

public class MySQLFactoryDAO {

	public MySQLNaloziDAO getMySQLNaloziDAO() {
		return new MySQLNaloziDAO();
	}
	
	public MySQLAktivnostDAO getMySQLAktivnostDAO() {
		return new MySQLAktivnostDAO();
	}
	
	public MySQLPopisniKrugDAO getMySQLPopisniKrugDAO() {
		return new MySQLPopisniKrugDAO();
	}
	
	public MySQLOpstinaDAO getMySQLOpstinaDAO() {
		return new MySQLOpstinaDAO();
	}
	
	public MySQLKontrolnikDAO getMySQLKontorlnikDAO() {
		return new MySQLKontrolnikDAO();
	}
	
}
