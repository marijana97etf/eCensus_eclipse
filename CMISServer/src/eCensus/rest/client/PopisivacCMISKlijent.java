package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;

public class PopisivacCMISKlijent extends CMISKlijent {

	{
		korisnikResursUrl = naloziResursUrl + "/popisivac";
	}
	
	public PopisivacCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
	}

	public PopisivacCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}
	
	public Response getListaPopisivaca(){
		return get(cmisResursUrl + "/" + naloziResursUrl + "/lista?tip=" + Popisivac.class.getName() );
	}
	
	public Response getPopisneKrugovePopisivaca(Popisivac popisivac){
		return null;
	}
	
	public Response dodijeliPopisneKrugovePopisivaci(Popisivac popisivac) {
		return null;
	}
	
	public Response azurirajPopisneKrugovePopisivaca(Popisivac popisivac) {
		return null;
	}
	
	public Response sacuvajOcjenuPopisivaca() {
		return null;
	}
}
