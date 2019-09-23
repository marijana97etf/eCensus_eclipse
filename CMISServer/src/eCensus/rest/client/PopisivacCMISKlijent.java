package eCensus.rest.client;

import java.util.List;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

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
	
	public Response getPopisivac(String korisnickoIme) {
		return get(cmisResursUrl + "/" + naloziResursUrl + "/" + korisnickoIme);
	}
	
	public Response getListaPopisivaca(){
		return get(cmisResursUrl + "/" + korisnikResursUrl + "/lista?tip=" + Popisivac.class.getName() );
	}
	
	public Response getPopisneKrugovePopisivaca(Popisivac popisivac){
		System.out.println(cmisResursUrl + "/" + naloziResursUrl + "/" + popisivac.getKorisnickoIme() + "/popisniKrugovi");
		return get(cmisResursUrl + "/" + korisnikResursUrl + "/" + popisivac.getKorisnickoIme() + "/popisniKrugovi");
	}
	
	public Response dodajPopisneKrugovePopisivacu(Popisivac popisivac,List<PopisniKrug> popisniKrugovi) {
		return post(cmisResursUrl + "/" + korisnikResursUrl + "/" + popisivac.getKorisnickoIme() + "/popisniKrugovi",popisniKrugovi);
	}
	
	public Response azurirajPopisneKrugovePopisivaca(Popisivac popisivac,List<PopisniKrug> popisniKrugovi) {
		return put(cmisResursUrl + "/" + korisnikResursUrl + "/" + popisivac.getKorisnickoIme() + "/popisniKrugovi",popisniKrugovi);
	}
	
	public Response sacuvajOcjenuPopisivaca() {
		return null;
	}
}
