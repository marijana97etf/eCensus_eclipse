package eCensus.rest.client;

import model.korisnicki_nalozi.KorisnikSistema;

public abstract class AdministratorCMISKlijent extends CMISKlijent {

	public AdministratorCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public AdministratorCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore,
			String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}
	
	

}
