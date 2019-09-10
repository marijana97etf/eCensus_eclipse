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
		KORISNIK_RESURS_URL = NALOZI_RESURS_URL + "/clanPKLS";
	}
	
	public ClanPKLSCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public ClanPKLSCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

	public ClanPKLS getClanPKLS(String korisnickoIme) {
		return get(CMIS_RESURS_URL + "/" + NALOZI_RESURS_URL + "/" + korisnickoIme).readEntity(ClanPKLS.class);
	}

	//provjeri
	@SuppressWarnings("unchecked")
	public List<ClanPKLS> getListuClanovaPKLS(){
		return get(CMIS_RESURS_URL + "/" + NALOZI_RESURS_URL + "/lista?tip=" + ClanPKLS.class.getName() )
				.readEntity(new GenericType<LinkedList<ClanPKLS>>() {});
	}

	@Override
	protected KorisnikSistema readEntity(Response odgovor) {
		return odgovor.readEntity(ClanPKLS.class);
	}
}
