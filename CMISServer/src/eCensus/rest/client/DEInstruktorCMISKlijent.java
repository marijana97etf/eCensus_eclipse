package eCensus.rest.client;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;

public class DEInstruktorCMISKlijent extends AdministratorCMISKlijent {
	
	{
		KORISNIK_RESURS_URL = NALOZI_RESURS_URL + "/dEInstruktor";
	}

	public DEInstruktorCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public DEInstruktorCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}
	
	public List<DEInstruktor> getListuDEInstruktora(){
		return get(CMIS_RESURS_URL + "/" + NALOZI_RESURS_URL + "/lista?tip=" + DEInstruktor.class.getName() )
				.readEntity(new GenericType<LinkedList<DEInstruktor>>() {});
	}

	@Override
	protected KorisnikSistema readEntity(Response odgovor) {
		return odgovor.readEntity(DEInstruktor.class);
	}

}
