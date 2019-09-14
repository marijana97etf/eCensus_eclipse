package eCensus.rest.client;

import java.util.List;

import javax.ws.rs.core.Response;

import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;

public class PopisivacGlavniServerKlijent extends GlavniServerKlijent {

	public PopisivacGlavniServerKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}

	
	public boolean obradiPopisniceZaStanovnike(List<PopisnicaZaStanovnika> popisniceZaStanovnike) {
		Response response = post(GLAVNI_SERVER_RESURS_URL + "/" + "obradiPopisnice/stanovnici", popisniceZaStanovnike);
		if(response.getStatusInfo() != Response.Status.OK) {
			return false;
		}
		return true;
	}
	
	
	public boolean obradiPopisniceZaDomacinstva(List<PopisnicaZaDomacinstvo> popisniceZaDomacinstva) {
		Response response = post(GLAVNI_SERVER_RESURS_URL + "/" + "obradiPopisnice/domacinstva", popisniceZaDomacinstva);
		if(response.getStatusInfo() != Response.Status.OK) {
			return false;
		}
		return true;
	}
	
	
}
