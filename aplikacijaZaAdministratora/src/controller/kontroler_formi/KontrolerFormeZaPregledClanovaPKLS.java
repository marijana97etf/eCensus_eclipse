package controller.kontroler_formi;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.stream.Collectors;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import eCensus.rest.client.ClanPKLSCMISKlijent;
import javafx.collections.FXCollections;
import model.korisnicki_nalozi.ClanPKLS;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;

public class KontrolerFormeZaPregledClanovaPKLS extends KontrolerFormeZaPregledNaloga {
    @Override
    public void initializeList() {
    	
    	ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
    	Response odgovor  = clanPKLSCMISKlijent.getListuClanovaPKLS();
    	if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
    		lista = FXCollections.observableArrayList(odgovor.readEntity(new GenericType<LinkedList<ClanPKLS>>() {}).stream()
                    .map(KorisnikInputModel::new)
                    .collect(Collectors.toList()));
    	}else {
    		Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
    	}
    }
}
