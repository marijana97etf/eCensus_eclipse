package eCensus.rest.client;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;

public class OGInstruktorCMISKLijent extends AdministratorCMISKlijent {
	
	{
		KORISNIK_RESURS_URL = NALOZI_RESURS_URL + "/oGInstruktor";
	}

	public OGInstruktorCMISKLijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public OGInstruktorCMISKLijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}
	
	public Response getListuOGInstruktora(){
		return get(CMIS_RESURS_URL + "/" + NALOZI_RESURS_URL + "/lista?tip=" + OGInstruktor.class.getName() );
	}

	@Override
	protected KorisnikSistema readEntity(Response odgovor) {
		return odgovor.readEntity(OGInstruktor.class);
	}

}
