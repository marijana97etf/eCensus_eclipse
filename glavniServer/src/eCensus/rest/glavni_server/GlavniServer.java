package eCensus.rest.glavni_server;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import eCensus.dao.DAOFactory;
import eCensus.dao.PopisnicaZaDomacinstvoDAO;
import eCensus.dao.PopisnicaZaStanovnikaDAO;
import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;

@Path("/GlavniServer")
public class GlavniServer {

	//Obrada popisnica
	@POST
	@Path("obradiPopisnice/stanovnici")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obradiPopisniceZaStanovnike(PopisnicaZaStanovnika popisnicaZaStanovnika) {
		PopisnicaZaStanovnikaDAO stanovnikDAO = DAOFactory.getMySQLFactoryDAO().getPopisnicaZaStanovnikaDAO();
		boolean uspjesno = stanovnikDAO.skladistiPodatkeZaStanovnika(popisnicaZaStanovnika);
		if(uspjesno) {
			return Response.status(Status.OK).entity(true).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	@POST
	@Path("obradiPopisnice/domacinstva")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obradiPopisnicuODomacinstvu(PopisnicaZaDomacinstvo popisnicaZaDomacinstvo) {
		PopisnicaZaDomacinstvoDAO domacinstvoDAO = DAOFactory.getMySQLFactoryDAO().getPopisnicaZaDomacinstvoDAO();
		boolean uspjesno = domacinstvoDAO.skladistiPodatkeZaDomacinstvo(popisnicaZaDomacinstvo);
		if(uspjesno) {
			return Response.status(Status.OK).entity(true).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		
	}
	
	//Kreiranje statistickih izvjestaja 
	@GET
	@Path("statistickiIzvjestaj/starostIPol/{entitet}/{opstina}/{starost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaPojedinacnimGodinamaStarostiIPolu(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("starost") String starost, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikaPremaPojedinacnimGodinamaStarostiIPolu(entitet, opstina, starost, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/bracniStatus/{entitet}/{opstina}/{status}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaBracnomStatusuIPolu(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("status") String status, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikaPremaPremaBracnomStatusuIPolu(entitet, opstina, status, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/zenskoStanovnistvo/brojZivorodjeneDjece/{entitet}/{opstina}/{brojDjece}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajZenskogStanovnistvaPremaBrojuZivorodjeneDjece(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("brojDjece") String brojDjece) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojZenskogStanovnistvaPremaBrojuZivorodjeneDjece(entitet, opstina, brojDjece);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/nacionalnaPripadnost/{entitet}/{opstina}/{nacionalnaPripadnost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaNacionalnojPripadnosti(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("nacionalnaPripadnost") String nacionalnaPripadnost, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikaPremaNacionalnojPripadnosti(entitet, opstina, nacionalnaPripadnost, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/vjeroispovjest/{entitet}/{opstina}/{vjeroispovjest}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaVjeroispovjesti(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("vjeroispovjest") String vjeroispovjest, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikPremaVjeroispovjesti(entitet, opstina, vjeroispovjest, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/maternjiJezik/{entitet}/{opstina}/{maternjiJezik}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajMaternjemJeziku(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("maternjiJezik") String maternjiJezik, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikaPremaMaternjemJeziku(entitet, opstina, maternjiJezik, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/pismenost/{entitet}/{opstina}/{pismenost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaPismenosti(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("pismenost") String pismenost, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikaPremaPismenosti(entitet, opstina, pismenost, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/zavrsenaSkola/{entitet}/{opstina}/{zavrsenaSkola}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaZavrsenojSkoli(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("zavrsenaSkola") String zavrsenaSkola, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikaPremaZavrsenojSkoli(entitet, opstina, zavrsenaSkola, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/kompjuterskaPismenost/{entitet}/{opstina}/{kompjuterskaPismenost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaKompjuterskojPismenosti(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("kompjuterskaPismenost") String kompjuterskaPismenost, @PathParam("pol") String pol) {
		int brojStanovnika = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovnikaPremaKompjuterskojPismenosti(entitet, opstina, kompjuterskaPismenost, pol);
		if(brojStanovnika != -1) {
			return Response.status(Status.OK).entity(brojStanovnika).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/domacinstvo/brojClanova/{entitet}/{opstina}/{brojClanova}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajDomacinstvaPremaBrojuClanova(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("brojClanova") String brojClanova) {
		int brojDomacinstava = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojDomacinstavaPremaBrojuClanova(entitet, opstina, brojClanova);
		if(brojDomacinstava != -1) {
			return Response.status(Status.OK).entity(brojDomacinstava).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/domacinstvo/poljoprivrednaAktivnost/{entitet}/{opstina}/{poljoprivreda}/{prodaja}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajDomacinstvaPremaPoljoprivrednojAktivnosti(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("poljoprivreda") String poljoprivreda, @PathParam("prodaja") String prodaja) {
		int brojDomacinstava = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojDomacinstavaPremaPoljoprivrednojAktivnosti(entitet, opstina, poljoprivreda, prodaja);
		if(brojDomacinstava != -1) {
			return Response.status(Status.OK).entity(brojDomacinstava).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/zgrade/brojuStanova/{entitet}/{opstina}/{brojStanova}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajZgradaPremaBrojuStanova(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("brojStanova") String brojStanova) {
		int brojZgrada = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojZgradaPremaBrojuStanova(entitet, opstina, brojStanova);
		if(brojZgrada != -1) {
			return Response.status(Status.OK).entity(brojZgrada).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/stan/brojSoba/{entitet}/{opstina}/{brojSoba}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajStanovaPremaBrojuOsoba(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("brojSoba") String brojSoba) {
		int brojStanova = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getBrojStanovaPremaBrojuSoba(entitet, opstina, brojSoba);
		if(brojStanova != -1) {
			return Response.status(Status.OK).entity(brojStanova).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@GET
	@Path("statistickiIzvjestaj/stan/povrsina/{entitet}/{opstina}/{brojSoba}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajStanovaPremaPovrsini(@PathParam("entitet") String entitet,@PathParam("opstina") String opstina, @PathParam("brojSoba") String brojSoba) {
		int ukupnaPovrsinaStanova = DAOFactory.getMySQLFactoryDAO().getMySQLStatistickiPodaciDAO().getPovrsinaStanovaPremaBrojuSoba(entitet, opstina, brojSoba);
		if(ukupnaPovrsinaStanova != -1) {
			return Response.status(Status.OK).entity(ukupnaPovrsinaStanova).build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
