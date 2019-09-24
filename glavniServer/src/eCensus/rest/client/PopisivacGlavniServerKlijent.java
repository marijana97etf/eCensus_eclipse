package eCensus.rest.client;

import java.util.List;

import javax.ws.rs.core.Response;

import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;

public class PopisivacGlavniServerKlijent extends GlavniServerKlijent {

	public PopisivacGlavniServerKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}

	
	public int obradiPopisniceZaStanovnike(List<PopisnicaZaStanovnika> popisniceZaStanovnike) {
		Response response = post(GLAVNI_SERVER_RESURS_URL + "/" + "obradiPopisnice/stanovnici", popisniceZaStanovnike);
		return response.getStatus();
	}
	
	
	public int obradiPopisniceZaDomacinstva(List<PopisnicaZaDomacinstvo> popisniceZaDomacinstva) {
		Response response = post(GLAVNI_SERVER_RESURS_URL + "/" + "obradiPopisnice/domacinstva", popisniceZaDomacinstva);
		return response.getStatus();
	}
	
	
}
