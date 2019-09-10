package eCensus.rest.client;

import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

public class SecureRestKlijent {

	protected Client klijent;
	
	public SecureRestKlijent(String keyStore,String keyStoreLozinka,String trustStore,String trustStoreLozinka,String korisnickoIme,String lozinkaHash) {
		
		SslConfigurator sslConfig = SslConfigurator.newInstance()
		        .trustStoreFile(trustStore)
		        .trustStorePassword(trustStoreLozinka)
		        .keyStoreFile(keyStore)
		        .keyPassword(keyStoreLozinka);
		 
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(korisnickoIme, lozinkaHash);
		
		SSLContext sslContext = sslConfig.createSSLContext();
		klijent = ClientBuilder.newBuilder().sslContext(sslContext).build();
		klijent.register(feature);
		
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
