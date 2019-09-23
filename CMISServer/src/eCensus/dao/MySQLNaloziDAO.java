package eCensus.dao;

import java.util.Collection;

import model.korisnicki_nalozi.KorisnikSistema;

public class MySQLNaloziDAO {

	public MySQLPopisivacDAO getMySQLPopisivacDAO() {
		return new MySQLPopisivacDAO();
	}
	
	public MySQLAdministratorAgencijeDAO getMySQLAdministratorAgencijeDAO() {
		return new MySQLAdministratorAgencijeDAO();
	}
	
	public MySQLClanPKLSDAO getMySQLClanPKLSDAO() {
		return new MySQLClanPKLSDAO();
	}
	
	public MySQLDEInstruktorDAO getMySQLDEInstruktorDAO() {
		return new MySQLDEInstruktorDAO();
	}
	
	public MySQLOGInstruktorDAO getMySQLOGInstruktorDAO() {
		return new MySQLOGInstruktorDAO();
	}
	
	public MySQLPowerUserDAO getMySQLPowerUserDAO() {
		return new MySQLPowerUserDAO();
	}
	
	public Collection<KorisnikSistema> getListaKorisnika(String tip) {
		if("Popisivac".equals(tip)) {
			return getMySQLPopisivacDAO().getListuKorisnika();
		}  else if("AdministratorAgencije".equals(tip)) {
			return getMySQLAdministratorAgencijeDAO().getListuKorisnika();
		} else if("ClanPKLS".equals(tip)) {
			return getMySQLClanPKLSDAO().getListuKorisnika();
		} else if("DEInstruktor".equals(tip)) {
			return getMySQLDEInstruktorDAO().getListuKorisnika();
		} else if("OGInstruktor".equals(tip)) {
			return getMySQLOGInstruktorDAO().getListuKorisnika();
		} else if("PowerUser".equals(tip)) {
			return getMySQLPowerUserDAO().getListuKorisnika();
		} else {
			return null;
		}
	}
	
	public KorisnikSistema getKorisnikSistema(String korisnickoIme) {
		KorisnikSistema korisnikSistema = null;
		
		korisnikSistema = this.getMySQLPopisivacDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return korisnikSistema;
		}
		
		korisnikSistema = this.getMySQLAdministratorAgencijeDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return korisnikSistema;
		}
		
		korisnikSistema = this.getMySQLClanPKLSDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return korisnikSistema;
		}
		
		korisnikSistema = this.getMySQLDEInstruktorDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return korisnikSistema;
		}
		
		korisnikSistema = this.getMySQLOGInstruktorDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return korisnikSistema;
		}
		
		korisnikSistema = this.getMySQLPowerUserDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return korisnikSistema;
		}
		
		return null;
	}
	
}
