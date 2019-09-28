package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

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

	public Response azurirajOcjenuPopisivaca(int idOGInstruktora, int idPopisivaca, int ocjena) {
		return post(cmisResursUrl + "/" + "korisnici" + "/" + "ocjene" + "/" + "oGInstruktor" + "/" + idOGInstruktora + "/" + "popisivac" + "/" + idPopisivaca, ocjena);
	}
	
	public Response getListaPopisnihKrugova(String grad, String opstina) {
		return get(cmisResursUrl + "/" + "korisnici" + "/" + "popisniKrugovi" + "/" + grad + "/" + opstina);
	}
	
	public Response dodajPopisniKrug(PopisniKrug popisniKrug) {
		return post(cmisResursUrl + "/" + "korisnici" + "/" + "popisniKrugovi", popisniKrug);
	}
	
	public Response obrisiPopisniKrug(int idPopisnogKruga) {
		return delete(cmisResursUrl + "/" + "korisnici" + "/" + "popisniKrugovi" + "/" + idPopisnogKruga);
	}
	
}
