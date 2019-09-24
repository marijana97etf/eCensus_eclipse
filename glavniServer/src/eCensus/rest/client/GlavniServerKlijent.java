package eCensus.rest.client;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;
import java.util.logging.Level;

import model.korisnicki_nalozi.KorisnikSistema;

public class GlavniServerKlijent extends SecureRestKlijent {

	protected KorisnikSistema korisnikSistema;
	protected static final String GLAVNI_SERVER_CONFIG_FILE = "resources" + File.separator + "glavniServerConfig.properties";
	protected static String glavniServerResursURL;

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
		
		try(Reader configReader = new FileReader(GLAVNI_SERVER_CONFIG_FILE)){
			Properties properties = new Properties();
			properties.load(configReader);
			glavniServerResursURL = properties.getProperty("GLAVNI_SERVER_RESURS_URL");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GlavniServerKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
	}
	
	public GlavniServerKlijent(KorisnikSistema korisnikSistema) {
		super(korisnikSistema.getKeyStore(), korisnikSistema.getKeyLozinka(), korisnikSistema.getTrustStore(), korisnikSistema.getTrustLozinka(),
				korisnikSistema.getKorisnickoIme(), korisnikSistema.getLozinkaHash());
		this.korisnikSistema = korisnikSistema;
	}
	
}
