package eCensus.rest.client;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;

public class AdministratorAgencijeCMISKlijent extends AdministratorCMISKlijent {

	{
		KORISNIK_RESURS_URL = NALOZI_RESURS_URL + "/adminAgencije";
	}
	
	public AdministratorAgencijeCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public AdministratorAgencijeCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore,
			String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

	public boolean skladistiPodatkeOAdminuAgencije(AdministratorAgencije adminAgencije) {
		return true;
	}

	//provjeri
	@SuppressWarnings("unchecked")
	public Response getListuClanovaPKLS(){
		return get(CMIS_RESURS_URL + "/" + NALOZI_RESURS_URL + "/lista?tip=" + AdministratorAgencije.class.getName() );
	}

	@Override
	protected KorisnikSistema readEntity(Response odgovor) {
		return odgovor.readEntity(AdministratorAgencije.class);
	}

}
