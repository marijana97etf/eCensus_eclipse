package eCensus.rest.client;

public class GlavniServerKlijent extends SecureRestKlijent {

	protected static final String GLAVNI_SERVER_RESURS_URL = "https://localhost:8443/glavniServer/rest/GlavniServer";
	protected  String KORISNIK_RESURS_URL;
	
	public static String TRUSTSTORE = "D:\\Desktop\\eCensus_eclipse-master\\CMISServer\\resources\\clientTrustStore.p12";
	public static String KEYSTORE = "D:\\Desktop\\eCensus_eclipse-master\\CMISServer\\resources\\clientStore.p12";

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
	
	public GlavniServerKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}
	
	
}
