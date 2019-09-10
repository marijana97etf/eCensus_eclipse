package eCensus.rest.client;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.PowerUser;

public class PowerUserCMISKlijent extends CMISKlijent {
	
	{
		KORISNIK_RESURS_URL = NALOZI_RESURS_URL + "/powerUser";
	}

	public PowerUserCMISKlijent(KorisnikSistema korisnik) {
		super(korisnik);
		// TODO Auto-generated constructor stub
	}

	public PowerUserCMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected KorisnikSistema readEntity(Response odgovor) {
		return odgovor.readEntity(PowerUser.class);
	}
}
