package eCensus.rest.cmis;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.sun.jersey.core.util.Base64;

import eCensus.dao.NaloziDAO;
import model.korisnicki_nalozi.KorisnikSistema;

@Provider
public class AutentikacijaFilter implements ContainerRequestFilter
{
     
    @Context
    private ResourceInfo resursInfo;
     
    private static final String AUTHORIZATION_PROPERTY = "Authorization";
    private static final String AUTHENTICATION_SCHEME = "Basic";
      
    @Override
    public void filter(ContainerRequestContext kontekstZahtjeva)
    {
    	
        final MultivaluedMap<String, String> zaglavlja = kontekstZahtjeva.getHeaders();
        
        final List<String> autentikacija = zaglavlja.get(AUTHORIZATION_PROPERTY);
          
        if(autentikacija == null || autentikacija.isEmpty())
        {
            kontekstZahtjeva.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }
          
        final String enkodovanoImeLozinka = autentikacija.get(0).replaceFirst(AUTHENTICATION_SCHEME + " ", "");
          
        String imeLozinka = new String(Base64.decode(enkodovanoImeLozinka.getBytes()));;

        final StringTokenizer tokenizer = new StringTokenizer(imeLozinka, ":");
        final String korisnickoIme = tokenizer.nextToken();
        final String lozinkaHash = tokenizer.nextToken();
        
        if( ! isUserAllowed(korisnickoIme, lozinkaHash, null))
        {
            kontekstZahtjeva.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                .entity("Nije dozvoljen pristup").build());
            return;
        }
    }
    
    
    private boolean isUserAllowed(final String korisnicnoIme, final String lozinkaHash, final Set<String> rolesSet)
    {
    	Collection<KorisnikSistema> korisnici = new NaloziDAO().getListuKorisnika();
    	for(KorisnikSistema korisnikSistema : korisnici) {
        	if(korisnicnoIme.equals(korisnikSistema.getKorisnickoIme()) && lozinkaHash.equals(korisnikSistema.getLozinkaHash()))
        		return true;
    	}
        return false;
    }
}