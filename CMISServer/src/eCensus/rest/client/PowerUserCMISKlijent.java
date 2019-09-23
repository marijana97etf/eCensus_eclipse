package eCensus.rest.client;

import model.korisnicki_nalozi.KorisnikSistema;

public class PowerUserCMISKlijent extends CMISKlijent {
	
	{
		korisnikResursUrl = naloziResursUrl + "/powerUser";
	}

	public PowerUserCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public PowerUserCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

}
