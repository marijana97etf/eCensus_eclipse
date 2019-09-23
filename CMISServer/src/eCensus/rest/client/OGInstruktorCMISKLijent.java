package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;

public class OGInstruktorCMISKLijent extends AdministratorCMISKlijent {
	
	{
		korisnikResursUrl = naloziResursUrl + "/oGInstruktor";
	}

	public OGInstruktorCMISKLijent(KorisnikSistema korisnik) {
		super(korisnik);
	}

	public OGInstruktorCMISKLijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}
	
	public Response getListuOGInstruktora(){
		return get(cmisResursUrl + "/" + naloziResursUrl + "/lista?tip=" + OGInstruktor.class.getName() );
	}

}
