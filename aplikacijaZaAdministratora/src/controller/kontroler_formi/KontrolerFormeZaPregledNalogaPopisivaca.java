package controller.kontroler_formi;

import javafx.collections.FXCollections;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.OGInstruktor;
import model.korisnicki_nalozi.Popisivac;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import eCensus.rest.client.DEInstruktorCMISKlijent;
import eCensus.rest.client.OGInstruktorCMISKLijent;
import eCensus.rest.client.PopisivacCMISKlijent;

public class KontrolerFormeZaPregledNalogaPopisivaca extends KontrolerFormeZaPregledNaloga {
    @Override
    public void initializeList() {
    	PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
    	Response odgovor  = popisivacCMISKlijent.getListaPopisivaca();
    	if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
    		lista = FXCollections.observableArrayList(odgovor.readEntity(new GenericType<LinkedList<Popisivac>>() {}).stream()
                    .map(KorisnikInputModel::new)
                    .collect(Collectors.toList()));
    	}else {
    		Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
    	}
    }
}
