package controller.kontroler_formi;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import eCensus.rest.client.DEInstruktorCMISKlijent;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import model.korisnicki_nalozi.DEInstruktor;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;

public class KontrolerFormeZaPregledDEInstruktora extends KontrolerFormeZaPregledNaloga {


    @FXML
    Label labelaZaIme;


    @Override
    public boolean initializeList() {
    	DEInstruktorCMISKlijent deInstruktorCMISKlijent = new DEInstruktorCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
    	Response odgovor  = deInstruktorCMISKlijent.getListuDEInstruktora();
    	if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
    		lista = FXCollections.observableArrayList(odgovor.readEntity(new GenericType<LinkedList<DEInstruktor>>() {}).stream()
                    .map(KorisnikInputModel::new)
                    .collect(Collectors.toList()));
    		return true;
    	}else {
    		Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
    	}
    	return false;
    }
}
