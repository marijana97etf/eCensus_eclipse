package eCensus.rest.client;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

import eCensus.rest.cmis.CMISKlijentBean;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;

public abstract class CMISKlijent extends SecureRestKlijent {

	protected KorisnikSistema korisnik;
	protected static  final String CMIS_RESURS_URL = "https://localhost:8443/CMISServer/rest/CMIS";
	protected static final String NALOZI_RESURS_URL = "korisnici/nalozi";
	protected  String KORISNIK_RESURS_URL;
	
	public static String TRUSTSTORE = "resources" + File.separator + "clientTrustStore.p12";
	public static String KEYSTORE = "resources" + File.separator + "clientStore.p12";

	static {
		// for localhost testing only
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			}
		});
	}

	public KorisnikSistema login(String korisnickoIme, String lozinkaHash) {
		Response odgovor = get(CMIS_RESURS_URL);
		if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
			if(korisnik==null) {
				 odgovor = get(CMIS_RESURS_URL + "/korisnici/nalozi/" + korisnickoIme);
				 if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
					 korisnik = readEntity(odgovor);
				 }else {
					 //mjesto za log
					 for(String key : odgovor.getHeaders().keySet())
						 System.out.println(odgovor.getHeaders().get(key));
				 } 
			}
		}
		return korisnik;
	}
	
	protected abstract KorisnikSistema readEntity(Response odgovor);

	public CMISKlijent(KorisnikSistema korisnik) {
		super(korisnik.getKeyStore(), korisnik.getKeyLozinka(), korisnik.getTrustStore(), korisnik.getTrustLozinka(),
				korisnik.getKorisnickoIme(), korisnik.getLozinkaHash());
		this.korisnik = korisnik;
	}

	public CMISKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka,
			String korisnickoIme, String lozinkaHash) {
		super(keyStore,keyStoreLozinka,trustStore,trustStoreLozinka,korisnickoIme,lozinkaHash);
	}
	
	public Response registrujKorisnika(KorisnikSistema korisnikSistema) {
		return post(CMIS_RESURS_URL + "/" + KORISNIK_RESURS_URL, korisnikSistema );
		
	}

	public Response obrisiKorisnika(KorisnikSistema korisnikSistema) {
		return delete(CMIS_RESURS_URL + "/" + KORISNIK_RESURS_URL + "/" + korisnikSistema.getId());
	}

	public Response azurirajKorisnika(KorisnikSistema korisnikSistema) {
		return put(CMIS_RESURS_URL + "/" + KORISNIK_RESURS_URL, korisnikSistema );
		
	}

}
