package eCensus.rest.client;

import java.util.List;

import javax.ws.rs.core.Response;

import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;
import model.korisnicki_nalozi.KorisnikSistema;

public class PopisivacGlavniServerKlijent extends GlavniServerKlijent {
	
	public PopisivacGlavniServerKlijent(KorisnikSistema korisnikSistema) {
		super(korisnikSistema);
	}
	
	public PopisivacGlavniServerKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}

	
	public int obradiPopisniceZaStanovnike(PopisnicaZaStanovnika popisnicaZaStanovnika) {
		Response response = post(glavniServerResursURL + "/" + "obradiPopisnice/stanovnici", popisnicaZaStanovnika);
		return response.getStatus();
	}
	
	
	public int obradiPopisniceZaDomacinstva(PopisnicaZaDomacinstvo popisnicaZaDomacinstvo) {
		Response response = post(glavniServerResursURL + "/" + "obradiPopisnice/domacinstva", popisnicaZaDomacinstvo);
		return response.getStatus();
	}
	
	
}
