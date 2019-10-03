package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.ws.rs.ProcessingException;

import controller.kontroler_formi.KontrolerFormeZaPrijavu;
import eCensus.rest.client.AdministratorCMISKlijent;
import eCensus.rest.client.CMISKlijent;
import test.Aplikacija;

public class OpstineCollection {

	private static Map<String, String> mapaOpstina;
	private static List<String> listaOpstina;
	
	static {
		try {
			AdministratorCMISKlijent administratorCMISKlijent = new AdministratorCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
			mapaOpstina = (Map<String, String>) administratorCMISKlijent.getMapaOpstina().readEntity(Map.class);
			listaOpstina = mapaOpstina.keySet().stream().sorted().collect(Collectors.toList());
		} catch (ProcessingException e) {
			Aplikacija.connLogger.getLogger().log(Level.WARNING, "Nema internet konekcije.");
			mapaOpstina = new HashMap<>();
			listaOpstina = new ArrayList<>();
		}
	}

	public static Map<String, String> getOpstine() {
		return mapaOpstina;
	}

	private static void setOpstine(Map<String, String> mapaOpstina) {
		OpstineCollection.mapaOpstina = mapaOpstina;
	}
	
	private OpstineCollection() {}
	
	public static List<String> getListaOpstina() {
		return listaOpstina;
	}
}
