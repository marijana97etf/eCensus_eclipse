package controller.kontroler_formi;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import eCensus.rest.client.OGInstruktorCMISKLijent;
import javafx.collections.FXCollections;
import model.korisnicki_nalozi.OGInstruktor;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;

public class KontrolerFormeZaPregledNalogaOGInstruktora extends KontrolerFormeZaPregledNaloga {
    @Override
    public boolean initializeList() {
    	OGInstruktorCMISKLijent ogInstruktorCMISKlijent = new OGInstruktorCMISKLijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
    	Response odgovor  = ogInstruktorCMISKlijent.getListuOGInstruktora();
    	if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
    		lista = FXCollections.observableArrayList(odgovor.readEntity(new GenericType<LinkedList<OGInstruktor>>() {}).stream()
                    .map(KorisnikInputModel::new)
                    .collect(Collectors.toList()));
    		return true;
    	}else {
    		Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
    	}
    	return false;
    }
}
