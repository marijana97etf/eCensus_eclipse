package eCensus.rest.cmis;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eCensus.dao.NaloziDAO;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.korisnicki_nalozi.Popisivac;
import model.korisnicki_nalozi.PowerUser;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

@Path("/CMIS")
public class CMISServis {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginInfo(String korisnickoIme) {
		KorisnikSistema korisnikRezultat = new NaloziDAO().getKorisnikSistema(korisnickoIme);
		
		if (korisnikRezultat == null) {
			return Response.noContent().build();
		} else
			return Response.ok().entity(korisnikRezultat.getClass().getName()).build();
	}

	@GET
	@Path("korisnici/nalozi/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKorisnikaSistema(@PathParam(value = "id") String korisnickoIme) {
		KorisnikSistema korisnikRezultat = new NaloziDAO().getKorisnikSistema(korisnickoIme);
		
		if (korisnikRezultat == null) {
			return Response.noContent().build();
		} else
			return Response.ok().entity(korisnikRezultat).build();
	}

	@GET
	@Path("korisnici/nalozi/lista")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListuKorisnika(@QueryParam("tip") String tip) {
		
		LinkedList<KorisnikSistema> lista = new LinkedList<>();
		Collection<KorisnikSistema> korisnici = new NaloziDAO().getListuKorisnika();
		for (KorisnikSistema korisnik : korisnici)
			if (korisnik.getClass().getName().equals(tip))
				lista.add(korisnik);
		return Response.ok().entity(new GenericEntity<LinkedList<KorisnikSistema>>(lista) {}).build();
	}

	protected Response registrujKorisnika(KorisnikSistema korisnikSistema) {
		if (korisnikSistema == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("Korisnik sistema je null").build();
		}
		new NaloziDAO().dodajKorisnika(korisnikSistema);
		return Response.status(Response.Status.CREATED).entity("Korisnik " + korisnikSistema.getKorisnickoIme() + "  uspjesno registrovan.")
				.build();
	}

	protected Response azurirajKorisnika(KorisnikSistema korisnikSistema) {
		if (!new NaloziDAO().sadrziKorisnika(korisnikSistema))
			return Response.status(Response.Status.BAD_REQUEST).entity("Korisnik nije registrovan.Ne moze se azurirati.").build();
		new NaloziDAO().azurirajKorisnika(korisnikSistema);
		return Response.ok().entity("Korisnik " + korisnikSistema.getKorisnickoIme() + "  uspjesno azuriran.").build();
	}

	protected Response obrisiKorisnika(Long id) {
		KorisnikSistema korisnik = new NaloziDAO().obrisiKorisnika(id);
		if (korisnik == null)
			return Response.status(Response.Status.BAD_REQUEST).entity("Odabrani korisnik nije pronadjen").build();
		else
			return Response.status(Response.Status.ACCEPTED).entity("Korisnik sa id = " + id + "  uspjesno obrisan.").build();
	}

	// metode clana PKLS
	@POST
	@Path("korisnici/nalozi/clanPKLS")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujClanaPKLS(ClanPKLS clanPKLS) {
		return registrujKorisnika(clanPKLS);
	}

	@PUT
	@Path("korisnici/nalozi/clanPKLS")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajClanaPKLS(ClanPKLS clanPKLS) {
		return azurirajKorisnika(clanPKLS);
	}

	@DELETE
	@Path("korisnici/nalozi/clanPKLS/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiClanaPKLS(@PathParam(value = "id") Long id) {
		return obrisiKorisnika(id);
	}
	
	//metode admina agencije
	@POST
	@Path("korisnici/nalozi/adminAgencije")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujAdminaAgencije(AdministratorAgencije administratorAgencije) {
		return registrujKorisnika(administratorAgencije);
	}

	@PUT
	@Path("korisnici/nalozi/adminAgencije")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajAdminaAgencije(AdministratorAgencije administratorAgencije) {
		return azurirajKorisnika(administratorAgencije);
	}

	@DELETE
	@Path("korisnici/nalozi/adminAgencije/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiAdminaAgencije(@PathParam(value = "id") Long id) {
		return obrisiKorisnika(id);
	}
	
	//metode DEInstruktora
	@POST
	@Path("korisnici/nalozi/dEInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujDEInstruktora(DEInstruktor deInstruktor) {
		return registrujKorisnika(deInstruktor);
	}

	@PUT
	@Path("korisnici/nalozi/dEInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajDEInstruktora(DEInstruktor deInstruktor) {
		return azurirajKorisnika(deInstruktor);
	}

	@DELETE
	@Path("korisnici/nalozi/dEInstruktor/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiDEInstruktora(@PathParam(value = "id") Long id) {
		return obrisiKorisnika(id);
	}
	
	//metode OGInstruktora
	@POST
	@Path("korisnici/nalozi/oGInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujOGInstruktora(OGInstruktor ogInstruktor) {
		return registrujKorisnika(ogInstruktor);
	}

	@PUT
	@Path("korisnici/nalozi/oGInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajOGInstruktora(OGInstruktor ogInstruktor) {
		return azurirajKorisnika(ogInstruktor);
	}

	@DELETE
	@Path("korisnici/nalozi/oGInstruktor/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiOGInstruktora(@PathParam(value = "id") Long id) {
		return obrisiKorisnika(id);
	}
		
	//metode PowerUser-a
		@POST
		@Path("korisnici/nalozi/powerUser")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response registrujPowerUsera(PowerUser powerUser) {
			return registrujKorisnika(powerUser);
		}
		
	//metode Popisivaca
		@POST
		@Path("korisnici/nalozi/popisivac")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response registrujPopisivaca(Popisivac popisivac) {
			return registrujKorisnika(popisivac);
		}

		@PUT
		@Path("korisnici/nalozi/popisivac")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response azurirajPopisivaca(Popisivac popisivac) {
			return azurirajKorisnika(popisivac);
		}

		@DELETE
		@Path("korisnici/nalozi/popisivac/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response obrisiPopisivaca(@PathParam(value = "id") Long id) {
			return obrisiKorisnika(id);
		}
		
		@GET
		@Path("korisnici/nalozi/popisivac/{id}/popisniKrugovi")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getPopisneKrugovePopisivaca(@PathParam(value = "id") String korisnickoIme) {
			Popisivac popisivacRezultat = (Popisivac)new NaloziDAO().getKorisnikSistema(korisnickoIme);
			if (popisivacRezultat == null) {
				return Response.noContent().build();
			} else
				return Response.ok().entity(new GenericEntity<List<PopisniKrug>>(popisivacRezultat.getdodijeljeniPopisniKrugovi()) {}).build();
		}
		
		@POST
		@Path("korisnici/nalozi/popisivac/{id}/popisniKrugovi")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response dodijeliPopisneKrugovePopisivaca(@PathParam(value = "id") String korisnickoIme,List<PopisniKrug> popisniKrugovi) {
			Popisivac popisivacRezultat = new NaloziDAO().dodajPopisneKrugovePopisivacu(korisnickoIme, popisniKrugovi);
			if (popisivacRezultat == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Ne postoji popisivac s tim korisnickim imenom.").build();
			}else
				return Response.status(Response.Status.CREATED).entity("Popisni krugovi uspjesno dodijeljeni.").build();
			
		}
		
		@PUT
		@Path("korisnici/nalozi/popisivac/{id}/popisniKrugovi")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response azurirajPopisneKrugovePopisivaca(@PathParam(value = "id") String korisnickoIme,List<PopisniKrug> popisniKrugovi) {
			Popisivac popisivacRezultat = new NaloziDAO().azurirajPopisneKrugove(korisnickoIme, popisniKrugovi);
			if (popisivacRezultat == null) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Ne postoji popisivac s tim korisnickim imenom.").build();
			}else
				return Response.ok().entity("Popisni krugovi uspjesno azurirani.").build();
		}
		
		@POST
		@Path("korisnici/ocjene/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response sacuvajOcjenuPopisivaca(@PathParam(value = "id") String korisnickoIme) {
			//TO DO
			
			
			return Response.noContent().build();
		}
		
	

}
