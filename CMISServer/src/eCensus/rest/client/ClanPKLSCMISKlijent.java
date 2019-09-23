package eCensus.rest.client;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;

public class ClanPKLSCMISKlijent extends AdministratorCMISKlijent {

	{
		korisnikResursUrl = naloziResursUrl + "/clanPKLS";
	}
	
	public ClanPKLSCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
	}

	public ClanPKLSCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}

	public Response getClanPKLS(String korisnickoIme) {
		return get(cmisResursUrl + "/" + naloziResursUrl + "/" + korisnickoIme);
	}
	
	@SuppressWarnings("unchecked")
	public Response getListuClanovaPKLS(){
		return get(cmisResursUrl + "/" + naloziResursUrl + "/lista?tip=" + ClanPKLS.class.getName() );
	}

}
