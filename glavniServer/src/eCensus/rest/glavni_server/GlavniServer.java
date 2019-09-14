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
	public Response obradiPopisniceZaStanovnike(List<PopisnicaZaStanovnika> popisniceZaStanovnike) {
		PopisnicaZaStanovnikaDAO stanovnikDAO = DAOFactory.getMySQLFactoryDAO().getPopisnicaZaStanovnikaDAO();
		for(PopisnicaZaStanovnika popisnicaZaStanovnika : popisniceZaStanovnike) {
			boolean uspjesno = stanovnikDAO.skladistiPodatkeZaStanovnika(popisnicaZaStanovnika);
			if(!uspjesno) {
				//obradi neuspjesno skladistenu popisnicuZaStanovinka
			}
		}
		return Response.status(Status.OK).entity(true).build();
	}
	
	
	@POST
	@Path("obradiPopisnice/domacinstva")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response obradiPopisnicuODomacinstvu(List<PopisnicaZaDomacinstvo> popisniceZaDomacinstva) {
		PopisnicaZaDomacinstvoDAO domacinstvoDAO = DAOFactory.getMySQLFactoryDAO().getPopisnicaZaDomacinstvoDAO();
		for(PopisnicaZaDomacinstvo popisnicaZaDomacinstvo : popisniceZaDomacinstva) {
			boolean uspjesno = domacinstvoDAO.skladistiPodatkeZaDomacinstvo(popisnicaZaDomacinstvo);
			if(!uspjesno) {
				//obradi neuspjesno skladistenu popisnicuZaStanovinka
			}
		}
		return Response.status(Status.OK).entity(true).build();
	}
	
	//Kreiranje statistickih izvjestaja 
	@GET
	@Path("statistickiIzvjestaj/starostIPol/{entitet}/{regija}/{naselje}/{starost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaPojedinacnimGodinamaStarostiIPolu(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("naselje") String naselje, @PathParam("starost") int starost, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/bracniStatus/{entitet}/{regija}/{status}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaBracnomStatusuIPolu(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("status") String status, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/zenskoStanovnistvo/brojZivorodjeneDjece/{entitet}/{regija}/{brojDjece}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajZenskogStanovnistvaPremaBrojuZivorodjeneDjece(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("brojDjece") String brojDjece) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/nacionalnaPripadnost/{entitet}/{regija}/{nacionalnaPripadnost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaNacionalnojPripadnosti(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("nacionalnaPripadnost") String nacionalnaPripadnost, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/vjeroispovjest/{entitet}/{regija}/{vjeroispovjest}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaVjeroispovjesti(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("vjeroispovjest") String vjeroispovjest, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/maternjiJezik/{entitet}/{regija}/{maternjiJezik}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajMaternjemJeziku(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("maternjiJezik") String maternjiJezik, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/pismenost/{entitet}/{regija}/{pismenost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaPismenosti(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("pismenost") String pismenost, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/zavrsenaSkola/{entitet}/{regija}/{zavrsenaSkola}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaZavrsenojSkoli(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("zavrsenaSkola") String zavrsenaSkola, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/kompjuterskaPismenost/{entitet}/{regija}/{kompjuterskaPismenost}/{pol}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajPremaKompjuterskojPismenosti(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("kompjuterskaPismenost") String kompjuterskaPismenost, @PathParam("pol") String pol) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/domacinstvo/brojClanova/{entitet}/{regija}/{brojClanova}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajDomacinstvaPremaBrojuClanova(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("brojClanova") int brojClanova) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/domacinstvo/poljoprivrednaAktivnost/{entitet}/{regija}/{poljoprivrednaAktivnost}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajDomacinstvaPremaPoljoprivrednojAktivnosti(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("poljoprivrednaAktivnost") String poljoprivrednaAktivnost) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/zgrade/brojuStanova/{entitet}/{regija}/{brojStanova}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajZgradaPremaBrojuStanova(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("brojStanova") int brojStanova) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/stan/brojOsoba/{entitet}/{regija}/{velicinaStana}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajStanovaPremaBrojuOsoba(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("velicinaStana") String velicinaStana) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
	
	@GET
	@Path("statistickiIzvjestaj/stan/povrsina/{entitet}/{regija}/{velicinaStana}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStatistickiIzvjestajStanovaPremaPovrsini(@PathParam("entitet") String entitet,@PathParam("regija") String regija, @PathParam("velicinaStana") String velicinaStana) {
		return Response.status(Status.NOT_IMPLEMENTED).entity(null).build();
	}
}
