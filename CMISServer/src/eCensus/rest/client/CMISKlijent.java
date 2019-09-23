package eCensus.rest.client;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;

import javax.ws.rs.core.Response;

import model.korisnicki_nalozi.KorisnikSistema;

public abstract class CMISKlijent extends SecureRestKlijent {

	protected KorisnikSistema korisnik;
	protected  String cmisResursUrl;
	protected  String naloziResursUrl;
	protected  String korisnikResursUrl;

	static {
		
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {

			public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
				if (hostname.equals("localhost")) {
					return true;
				}
				return false;
			}
		});
	}
	
	{
		try(Reader configReader = new FileReader(CMIS_CONFIG_FILE)){
			Properties properties = new Properties();
			properties.load(configReader);
			cmisResursUrl = properties.getProperty("CMIS_RESURS_URL");
			naloziResursUrl = properties.getProperty("NALOZI_RESURS_URL");
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} catch (IOException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		}
	}

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
		return post(cmisResursUrl + "/" + korisnikResursUrl, korisnikSistema );
		
	}

	public Response obrisiKorisnika(KorisnikSistema korisnikSistema) {
		return delete(cmisResursUrl + "/" + korisnikResursUrl + "/" + korisnikSistema.getId());
	}

	public Response azurirajKorisnika(KorisnikSistema korisnikSistema) {
		return put(cmisResursUrl + "/" + korisnikResursUrl, korisnikSistema );
		
	}

}
