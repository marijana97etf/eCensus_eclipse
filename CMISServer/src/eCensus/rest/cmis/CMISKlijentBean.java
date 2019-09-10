package eCensus.rest.cmis;

import model.korisnicki_nalozi.KorisnikSistema;

public class CMISKlijentBean {
	
	protected String tip;
	protected KorisnikSistema korisnikSistema;

	public CMISKlijentBean() {
	}
	
	public CMISKlijentBean(KorisnikSistema korisnikSistema,String tip) {
		this.korisnikSistema = korisnikSistema;
		this.tip = tip;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public KorisnikSistema getKorisnikSistema() {
		return korisnikSistema;
	}

	public void setKorisnikSistema(KorisnikSistema korisnikSistema) {
		this.korisnikSistema = korisnikSistema;
	}
	
	

}
