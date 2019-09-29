package eCensus.rest.cmis;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
import javax.ws.rs.core.Response.Status;

import eCensus.dao.DAOFactory;
import eCensus.dao.DavidovStariNaloziDAO;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.korisnicki_nalozi.Popisivac;
import model.korisnicki_nalozi.PowerUser;
import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

@Path("/CMIS")
public class CMISServis {

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response loginInfo(String korisnickoIme) {
		KorisnikSistema korisnikSistema = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return Response.status(Status.OK).entity(korisnikSistema.getClass().getName()).build();//Davide cemu ovo sluzi
		} else {
			return Response.status(Status.NO_CONTENT).build();
		}
	}

	@GET
	@Path("korisnici/nalozi/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getKorisnikaSistema(@PathParam(value = "id") String korisnickoIme) {
		KorisnikSistema korisnikSistema = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getKorisnikSistema(korisnickoIme);
		if(korisnikSistema != null) {
			return Response.status(Status.OK).entity(korisnikSistema).build();
		} else {
			return Response.status(Status.NO_CONTENT).build();
		}
	}

	@GET
	@Path("korisnici/nalozi/lista")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListuKorisnika(@QueryParam("tip") String tip) {
		Collection<KorisnikSistema> korisniciSistema = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getListaKorisnika(tip);
		if(korisniciSistema != null) {
			return Response.status(Status.OK).entity(korisniciSistema).build();
		} else {
			return Response.status(Status.NO_CONTENT).entity(korisniciSistema).build();
		}
	}
/*
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
*/
	// metode clana PKLS
	@POST
	@Path("korisnici/nalozi/clanPKLS")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujClanaPKLS(ClanPKLS clanPKLS) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLClanPKLSDAO().dodajKorisnika(clanPKLS);
		if(result) {
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("korisnici/nalozi/clanPKLS")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajClanaPKLS(ClanPKLS clanPKLS) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLClanPKLSDAO().azurirajKorisnika(clanPKLS);
		if(result) {
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@DELETE
	@Path("korisnici/nalozi/clanPKLS/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiClanaPKLS(@PathParam(value = "id") Long id) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLClanPKLSDAO().obrisiKorisnika(id);
		if(result) {
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	//metode admina agencije
	@POST
	@Path("korisnici/nalozi/adminAgencije")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujAdminaAgencije(AdministratorAgencije administratorAgencije) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLAdministratorAgencijeDAO().dodajKorisnika(administratorAgencije);
		if(result) {
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("korisnici/nalozi/adminAgencije")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajAdminaAgencije(AdministratorAgencije administratorAgencije) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLAdministratorAgencijeDAO().azurirajKorisnika(administratorAgencije);
		if(result) {
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@DELETE
	@Path("korisnici/nalozi/adminAgencije/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiAdminaAgencije(@PathParam(value = "id") Long id) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLAdministratorAgencijeDAO().obrisiKorisnika(id);
		if(result) {
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	//metode DEInstruktora
	@POST
	@Path("korisnici/nalozi/dEInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujDEInstruktora(DEInstruktor deInstruktor) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLDEInstruktorDAO().dodajKorisnika(deInstruktor);
		if(result) {
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("korisnici/nalozi/dEInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajDEInstruktora(DEInstruktor deInstruktor) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLDEInstruktorDAO().azurirajKorisnika(deInstruktor);
		if(result) {
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@DELETE
	@Path("korisnici/nalozi/dEInstruktor/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiDEInstruktora(@PathParam(value = "id") Long id) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLDEInstruktorDAO().obrisiKorisnika(id);
		if(result) {
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}
	
	//metode OGInstruktora
	@POST
	@Path("korisnici/nalozi/oGInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response registrujOGInstruktora(OGInstruktor ogInstruktor) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLOGInstruktorDAO().dodajKorisnika(ogInstruktor);
		if(result) {
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@PUT
	@Path("korisnici/nalozi/oGInstruktor")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response azurirajOGInstruktora(OGInstruktor ogInstruktor) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLOGInstruktorDAO().azurirajKorisnika(ogInstruktor);
		if(result) {
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}

	@DELETE
	@Path("korisnici/nalozi/oGInstruktor/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response obrisiOGInstruktora(@PathParam(value = "id") Long id) {
		boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLOGInstruktorDAO().obrisiKorisnika(id);
		if(result) {
			return Response.status(Status.CREATED).build();
		} else {
			return Response.status(Status.CONFLICT).build();
		}
	}
		
	//metode PowerUser-a
		@POST
		@Path("korisnici/nalozi/powerUser")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response registrujPowerUsera(PowerUser powerUser) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPowerUserDAO().dodajKorisnika(powerUser);
			if(result) {
				return Response.status(Status.CREATED).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		}
		
		@PUT
		@Path("korisnici/nalozi/powerUser")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response azurirajPowerUsera(PowerUser powerUser) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPowerUserDAO().azurirajKorisnika(powerUser);
			if(result) {
				return Response.status(Status.ACCEPTED).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		}
		
	//metode Popisivaca
		@POST
		@Path("korisnici/nalozi/popisivac")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response registrujPopisivaca(Popisivac popisivac) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPopisivacDAO().dodajKorisnika(popisivac);
			if(result) {
				return Response.status(Status.CREATED).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		}

		@PUT
		@Path("korisnici/nalozi/popisivac")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response azurirajPopisivaca(Popisivac popisivac) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPopisivacDAO().azurirajKorisnika(popisivac);
			if(result) {
				return Response.status(Status.ACCEPTED).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		}

		@DELETE
		@Path("korisnici/nalozi/popisivac/{id}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response obrisiPopisivaca(@PathParam(value = "id") Long id) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPopisivacDAO().obrisiKorisnika(id);
			if(result) {
				return Response.status(Status.ACCEPTED).build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		}
		
		@PUT
		@Path("korisnici/nalozi/popisivac/{id}/aktivnost")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response azurirajAktivnostPopisivaca(@PathParam("id") int id, DnevnaAktivnost dnevnaAktivnost) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLAktivnostDAO().azurirajAktivnost(id, dnevnaAktivnost);
			if(result) {
				return Response.status(Status.OK).entity(true).build();
			} else {
				return Response.status(Status.NO_CONTENT).entity(false).build();
			}
		}
		
		

		@GET
		@Path("korisnici/nalozi/popisivac/{idPopisivaca}/popisniKrugovi")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getPopisneKrugovePopisivaca(@PathParam("idPopisivaca") int idPopisivaca) {
			List<PopisniKrug> popisniKrugoviPopisivaca = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPopisivacDAO().getListaPopisnihKrugovaPopisivaca(idPopisivaca);
			if (popisniKrugoviPopisivaca != null) {
				return Response.status(Status.OK).entity(popisniKrugoviPopisivaca).build();
			} else {
				return Response.status(Status.NO_CONTENT).build();
			}
		}
		
		@POST
		@Path("korisnici/nalozi/popisivac/popisniKrugovi/{idPopisnogKruga}/{idOpstine}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response dodijeliPopisniKrugPopisivacu(int idPopisivaca, @PathParam("idPopisnogKruga") int idPopisnogKruga, @PathParam("idOpstine") int idOpstine) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPopisivacDAO().dodajPopisniKrug(idPopisivaca, idPopisnogKruga, idOpstine);
			if (result) {
				return Response.status(Status.OK).entity(true).build();
			} else {
				return Response.status(Status.NO_CONTENT).build();
			}
			
		}
		
		@DELETE
		@Path("korisnici/nalozi/popisivac/{idPopisivaca}/popisniKrugovi/{idPopisnogKruga}/{idOpstine}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response obrisiPopisniKrugPopisivaca(@PathParam("idPopisivaca") int idPopisivaca, @PathParam("idPopisnogKruga") int idPopisnogKruga, @PathParam("idOpstine") int idOpstine) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPopisivacDAO().obrisiPopisniKrug(idPopisivaca, idPopisnogKruga, idOpstine);
			if (result) {
				return Response.status(Status.OK).entity(true).build();
			} else {
				return Response.status(Status.NO_CONTENT).build();
			}
		}

		@POST
		@Path("korisnici/ocjene/oGInstruktor/{idOGInstruktora}/popisivac/{idPopisivaca}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response sacuvajOcjenuPopisivaca(@PathParam("idOGInstruktora") int idOGInstruktora, @PathParam("idPopisivaca") int idPopisivaca, int ocjena) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLNaloziDAO().getMySQLPopisivacDAO().azurirajOcjenu(idPopisivaca, idOGInstruktora, ocjena);
			if(result) {
				return Response.status(Status.OK).entity(true).build();
			} else {
				return Response.status(Status.NO_CONTENT).build();
			}
		}
		
		//Popisni Krugovi
		
		@GET
		@Path("korisnici/popisniKrugovi/{grad}/{idOpstine}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getListaPopisnihKrugova(@PathParam("grad") String grad, @PathParam("idOpstine") int idOpstine) {
			List<PopisniKrug> popisniKrugovi = DAOFactory.getMySQLFactoryDAO().getMySQLPopisniKrugDAO().getListaPopisnihKrugova(grad, idOpstine);
			if (popisniKrugovi != null) {
				return Response.status(Status.OK).entity(popisniKrugovi).build();
			} else
				return Response.status(Status.NO_CONTENT).build();
		}
		
		@POST
		@Path("korisnici/popisniKrugovi")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response dodajPopisniKrug(PopisniKrug popisniKrug) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLPopisniKrugDAO().dodajPopisniKrug(popisniKrug);
			if (result) {
				return Response.status(Status.OK).entity(true).build();
			}else
				return Response.status(Status.NO_CONTENT).build();
			
		}
		
		@DELETE
		@Path("korisnici/popisniKrugovi/{idPopisnogKruga}/{idOpstine}")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response dodajPopisniKrug(@PathParam("idPopisnogKruga") int idPopisnogKruga,@PathParam("idOpstine") int idOpstine) {
			boolean result = DAOFactory.getMySQLFactoryDAO().getMySQLPopisniKrugDAO().obrisiPopisniKrug(idPopisnogKruga, idOpstine);
			if (result) {
				return Response.status(Status.OK).entity(true).build();
			}else
				return Response.status(Status.NO_CONTENT).build();
			
		}
		
		@GET
		@Path("opstine")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public Response getListaOpstina() {
			Map<String,String> opstine = DAOFactory.getMySQLFactoryDAO().getMySQLOpstinaDAO().getMapaOpstina();
			if (opstine != null) {
				return Response.status(Status.OK).entity(opstine).build();
			} else
				return Response.status(Status.NO_CONTENT).build();
		}
		
}
