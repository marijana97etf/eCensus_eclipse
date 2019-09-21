package eCensus.rest.client;

import javax.ws.rs.core.Response;

public class AdministratorGlavniServerKlijent extends GlavniServerKlijent {

	public AdministratorGlavniServerKlijent(String keyStore, String keyStoreLozinka, String trustStore, String trustStoreLozinka, String korisnickoIme, String lozinkaHash) {
		super(keyStore, keyStoreLozinka, trustStore, trustStoreLozinka, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

	//Funkcije za izdvajanje trazenih podataka iz baze za popisivanje stanovnistva i domacinstva
	public int getBrojStanovnikaPremaPojedinacnimGodinamaStarostiIPolu(String idEntiteta, String idOpstine, String starost, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/starostIPol/" + idEntiteta + "/" + idOpstine + "/" + starost + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaPremaBracnomStatusuIPolu(String idEntiteta, String idOpstine, String status, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/bracniStatus/" + idEntiteta + "/" + idOpstine + "/" + status + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojZenskogStanovnistvaPremaBrojuZivorodjeneDjece(String idEntiteta,String idOpstine, String brojZivorodjeneDjece) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/zenskoStanovnistvo/brojZivorodjeneDjece/" + idEntiteta + "/" + idOpstine + "/" + brojZivorodjeneDjece);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaNacionalnojPripadnosti(String idEntiteta, String idOpstine, String nacionalnaPripadnost, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/nacionalnaPripadnost/" + idEntiteta + "/" + idOpstine + "/" + nacionalnaPripadnost + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovnikPremaVjeroispovjesti(String idEntiteta, String idOpstine, String vjeroispovjest, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/vjeroispovjest/" + idEntiteta + "/" + idOpstine + "/" + vjeroispovjest + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaMaternjemJeziku(String idEntiteta, String idOpstine, String maternjiJezik, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/maternjiJezik/" + idEntiteta + "/" + idOpstine + "/" + maternjiJezik + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaPismenosti(String idEntiteta, String idOpstine, String pismenost, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/pismenost/" + idEntiteta + "/" + idOpstine + "/" + pismenost + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaZavrsenojSkoli(String idEntiteta, String idOpstine, String zavrsenaSkola, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/zavrsenaSkola/" + idEntiteta + "/" + idOpstine + "/" + zavrsenaSkola + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovnikaPremaKompjuterskojPismenosti(String idEntiteta, String idOpstine, String kompjuterskaPismenost, String pol) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/kompjuterskaPismenost/" + idEntiteta + "/" + idOpstine + "/" + kompjuterskaPismenost + "/" + pol);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	
	//Domacinstvo
	
	public int getBrojDomacinstavaPremaBrojuClanova(String idEntiteta, String idOpstine, String brojClanova) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/domacinstvo/brojClanova/" + idEntiteta + "/" + idOpstine + "/" + brojClanova);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojDomacinstavaPremaPoljoprivrednojAktivnosti(String idEntiteta, String idOpstine, String poljoprivreda, String prodaja) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/domacinstvo/poljoprivrednaAktivnost/" + idEntiteta + "/" + idOpstine + "/" + poljoprivreda + "/" + prodaja);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	//Zgrade broj stanova
	public int getBrojZgradaPremaBrojuStanova(String idEntiteta, String idOpstine, String brojStanova) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/zgrade/brojuStanova/" + idEntiteta + "/" + idOpstine + "/" + brojStanova);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getBrojStanovaPremaBrojuSoba(String idEntiteta, String idOpstine, String brojSoba) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/stan/brojSoba/" + idEntiteta + "/" + idOpstine + "/" + brojSoba);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
	public int getPovrsinaStanovaPremaBrojuSoba(String idEntiteta, String idOpstine, String brojSoba) {
		Response response = get(GLAVNI_SERVER_RESURS_URL + "/" + "statistickiIzvjestaj/stan/povrsina/" + idEntiteta + "/" + idOpstine + "/" + brojSoba);
		if(response.getStatusInfo() == Response.Status.OK) {
			return response.readEntity(Integer.class);
		}
		return -1;
	}
	
}
