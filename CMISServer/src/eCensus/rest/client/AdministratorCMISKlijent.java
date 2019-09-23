package eCensus.rest.client;

import model.korisnicki_nalozi.KorisnikSistema;

public class AdministratorCMISKlijent extends CMISKlijent {

	public AdministratorCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
	}

	public AdministratorCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore,
			String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}


}
