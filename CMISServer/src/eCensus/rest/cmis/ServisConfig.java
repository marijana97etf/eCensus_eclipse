package eCensus.rest.cmis;

import java.util.Set;

import org.glassfish.jersey.server.ResourceConfig;

public class ServisConfig extends ResourceConfig {

	public ServisConfig() {

        register(AutentikacijaFilter.class);
	}

}
