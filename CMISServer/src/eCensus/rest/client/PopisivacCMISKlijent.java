package eCensus.rest.client;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class PopisivacCMISKlijent extends CMISKlijent {

	{
		KORISNIK_RESURS_URL = NALOZI_RESURS_URL + "/popisivac";
	}
	
	public PopisivacCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public PopisivacCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}
	
//	public List<Popisivac> getListaPopisivaca(){
//		return get(CMIS_RESURS_URL + "/" + NALOZI_RESURS_URL + "/lista?tip=" + Popisivac.class.getName() )
//				.readEntity(new GenericType<LinkedList<Popisivac>>() {});
//	}
	
	public Response getListaPopisivaca(){
		return get(CMIS_RESURS_URL + "/" + NALOZI_RESURS_URL + "/lista?tip=" + Popisivac.class.getName() );
	}
	
	public Response getPopisneKrugovePopisivaca(Popisivac popisivac){
		return null;
	}
	
	public Response azurirajPopisneKrugovePopisivaca(Popisivac popisivac) {
		return null;
	}
	
	public Response sacuvajOcjenuPopisivaca() {
		return null;
	}

	@Override
	protected KorisnikSistema readEntity(Response odgovor) {
		return odgovor.readEntity(Popisivac.class);
	}
}
