package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.Administrator;
import model.korisnicki_nalozi.KorisnikSistema;

public class AdministratorCMISKlijent extends CMISKlijent {

	public AdministratorCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public AdministratorCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore,
			String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected KorisnikSistema readEntity(Response odgovor) {
		return odgovor.readEntity(Administrator.class);
	}
	
	

}
