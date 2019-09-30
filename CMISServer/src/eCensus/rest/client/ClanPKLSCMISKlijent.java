package eCensus.rest.client;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

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
	
	public Response getListaPopisnihKrugova(String grad, int idOpstine) {
		return get(cmisResursUrl + "/" + "popisniKrugovi" + "/" + grad + "/" + idOpstine);
	}
	
	public Response dodajPopisniKrug(PopisniKrug popisniKrug) {
		return post(cmisResursUrl + "/" + "popisniKrugovi", popisniKrug);
	}
	
	public Response obrisiPopisniKrug(int idPopisnogKruga, int idOpstine) {
		return delete(cmisResursUrl + "/" + "popisniKrugovi" + "/" + idPopisnogKruga + "/" + idOpstine);
	}

}
