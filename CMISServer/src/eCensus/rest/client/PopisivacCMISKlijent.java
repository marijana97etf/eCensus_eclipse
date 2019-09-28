package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;

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
		return get(cmisResursUrl + "/" + naloziResursUrl + "/lista?tip=" + Popisivac.class.getName() );
	}
	
	public Response azurirajAktivostPopisivaca(int idPopisivaca, DnevnaAktivnost dnevnaAktivnost) {
		return put(cmisResursUrl + "/" + korisnikResursUrl + "/" + idPopisivaca + "/aktivnost", dnevnaAktivnost);
	}
	
	public Response getListaPopisnihKrugova(int idPopisivaca){
		return get(cmisResursUrl + "/" + korisnikResursUrl + "/" + idPopisivaca + "/popisniKrugovi");
	}
	
	public Response dodajPopisneKrugovePopisivacu(int idPopisivaca, int idPopisnogKruga) {
		return post(cmisResursUrl + "/" + korisnikResursUrl + "/" + idPopisivaca + "/popisniKrugovi", idPopisnogKruga);
	}
	
	public Response obrisiPopisniKrugPopisivaca(int idPopisivaca, int idPopisnogKruga) {
		return delete(cmisResursUrl + "/" + korisnikResursUrl + "/" + idPopisivaca + "/" + "popisniKrugovi" + "/" + idPopisnogKruga);
	}
	
	public Response sacuvajOcjenuPopisivaca() {
		return null;
	}
}
