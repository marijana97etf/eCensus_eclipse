package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.KorisnikSistema;

public class AdministratorAgencijeCMISKlijent extends AdministratorCMISKlijent {

	{
		korisnikResursUrl = naloziResursUrl + "/adminAgencije";
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

	@SuppressWarnings("unchecked")
	public Response getListuClanovaPKLS(){
		return get(cmisResursUrl + "/" + naloziResursUrl + "/lista?tip=" + AdministratorAgencije.class.getName() );
	}

}
