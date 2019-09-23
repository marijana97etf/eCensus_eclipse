package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;

public class DEInstruktorCMISKlijent extends AdministratorCMISKlijent {
	
	{
		korisnikResursUrl = naloziResursUrl + "/dEInstruktor";
	}

	public DEInstruktorCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
	}

	public DEInstruktorCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}
	
	public Response getListuDEInstruktora(){
		return get(cmisResursUrl + "/" + naloziResursUrl + "/lista?tip=" + DEInstruktor.class.getName() );
	}

}
