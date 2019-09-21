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

	protected Client klijent;
	
	public SecureRestKlijent(String keyStore,String keyStoreLozinka,String trustStore,String trustStoreLozinka,String korisnickoIme,String lozinkaHash) {
		
//		SslConfigurator sslConfig = SslConfigurator.newInstance()
//		        .trustStoreFile(trustStore)
//		        .trustStorePassword(trustStoreLozinka)
//		        .keyStoreFile(keyStore)
//		        .keyPassword(keyStoreLozinka);
		 
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		SSLContext sslContext = null;
		try {
			sslContext = SSLContext.getInstance("SSL");
			sslContext.init(keyManagers, trustManagers, new SecureRandom());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(korisnickoIme, lozinkaHash);
		
//		SSLContext sslContext = sslConfig.createSSLContext();
		klijent = ClientBuilder.newBuilder().sslContext(sslContext).build();
		klijent.register(feature);
		
	}
	
	public static KeyStore loadKeyStore(String keyStorePath, String keyStorePassword) {
		
		try {
			File keyStoreFile = new File(keyStorePath);
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			InputStream keyInput = new FileInputStream(keyStoreFile);
			keyStore.load(keyInput, keyStorePassword.toCharArray());
			keyInput.close();
			return keyStore;
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	protected Response get(String url){
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.get();
	}
	
	protected <T> Response put(String url,T t) {
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		return  invocationBuilder.put(Entity.entity(t, MediaType.APPLICATION_JSON));
		
	} 
	
	protected <T> Response post(String url,T t) {
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		return  invocationBuilder.post(Entity.entity(t, MediaType.APPLICATION_JSON));
		
	}
	
	protected <T> Response delete(String url) {
		WebTarget webTarget = klijent.target(url);
		
		Invocation.Builder invocationBuilder =  webTarget.request();
		return  invocationBuilder.delete();
		
	}
	

}
