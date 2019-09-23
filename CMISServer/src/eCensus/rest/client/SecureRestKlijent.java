package eCensus.rest.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class SecureRestKlijent {
	
	protected static final String CLIENT_LOG_FILE = "resources" + File.separator + "error.log";
	protected static final String CMIS_CONFIG_FILE = "resources" + File.separator + "cmisConfig.properties";
	
	protected Logger logger;
	protected Client klijent;
	
	public SecureRestKlijent() {
		logger = Logger.getLogger(this.getClass().getName());
		try {
			logger.addHandler(new FileHandler(CLIENT_LOG_FILE, true));
			logger.setLevel(Level.ALL);
		}catch(IOException e) {
			e.printStackTrace();
		}catch(SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public SecureRestKlijent(String keyStore,String keyStoreLozinka,String trustStore,String trustStoreLozinka,String korisnickoIme,String lozinkaHash) {
		this(); 
		
		KeyManager[] keyManagers = null;
		TrustManager[] trustManagers = null;
		
		try {
			
			KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyManagerFactory.init(loadKeyStore(keyStore,keyStoreLozinka), keyStoreLozinka.toCharArray());
			keyManagers = keyManagerFactory.getKeyManagers();
				
			TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustManagerFactory.init(loadKeyStore(trustStore,trustStoreLozinka));
			trustManagers = trustManagerFactory.getTrustManagers();
			
		} catch (UnrecoverableKeyException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} catch (KeyStoreException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		}
		
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(keyManagers, trustManagers, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} catch (KeyManagementException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		}
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(korisnickoIme, lozinkaHash);
		
		klijent = ClientBuilder.newBuilder().sslContext(sslContext).build();
		klijent.register(feature);
		
	}
	
	protected  KeyStore loadKeyStore(String keyStorePath, String keyStorePassword) {
		
		try {
			File keyStoreFile = new File(keyStorePath);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			InputStream keyInput = new FileInputStream(keyStoreFile);
			keyStore.load(keyInput, keyStorePassword.toCharArray());
			keyInput.close();
			return keyStore;
		} catch (KeyStoreException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} catch (CertificateException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		} catch (IOException e) {
			logger.log(Level.SEVERE,e.getMessage(),e);
		}

		return null;
	}
	
	public Response get(String url){
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.get();
	}
	
	public <T> Response put(String url,T t) {
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		return  invocationBuilder.put(Entity.entity(t, MediaType.APPLICATION_JSON));
		
	} 
	
	public <T> Response post(String url,T t) {
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		return  invocationBuilder.post(Entity.entity(t, MediaType.APPLICATION_JSON));
		
	}
	
	public <T> Response delete(String url) {
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request();
		return  invocationBuilder.delete();
		
	}

	public static String getClientLogFile() {
		return CLIENT_LOG_FILE;
	}

	public static String getCmisConfigFile() {
		return CMIS_CONFIG_FILE;
	}
	

}
