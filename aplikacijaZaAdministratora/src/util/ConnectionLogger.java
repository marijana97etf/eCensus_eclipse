package util;

import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

public class ConnectionLogger{
	
	protected Logger logger;
	
	public ConnectionLogger(Logger logger) {
		this.logger = logger;
	}
	
	public void logHeaders(Level nivo,Response odgovor) {
		
		StringBuilder sb = new StringBuilder("");
    	sb.append(odgovor.getStatusInfo().getStatusCode() + " " + odgovor.getStatusInfo().getReasonPhrase() + System.lineSeparator());
    	for(Entry<String,List<Object>> entry : odgovor.getHeaders().entrySet()) {
    		sb.append(entry.getKey() + " ");
    		for(Object objekat : entry.getValue())
    			sb.append(objekat +" ");
    		sb.append(System.lineSeparator());
    	}
    	logger.log(nivo, sb.toString());
		
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}
	
	

}
