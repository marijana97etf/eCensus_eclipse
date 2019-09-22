package controller.kontroler_formi;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;

import javax.ws.rs.core.Response;

import eCensus.rest.client.ClanPKLSCMISKlijent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.korisnicki_nalozi.KorisnikSistema;
import model.table_input_models.KorisnikInputModel;
import test.Pokreni_GUI_Aplikaciju;

public class KontrolerFormeZaIzmjenuNalogaClanaPKLS implements Initializable {

    @FXML
    TextField imeIzmjena;

    @FXML
    TextField prezimeIzmjena;

    @FXML
    TextField usernameIzmjena;


    public void izmjeni(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Еѕelite da sačuvate izmjene naloga administratora PKLS-a?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        
        var nalogInputModel = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        nalogInputModel.setPrezime(prezimeIzmjena.getText());
        nalogInputModel.setIme(imeIzmjena.getText());
        nalogInputModel.setKorisnickoIme(usernameIzmjena.getText());
        nalogInputModel.updateKorisnikSistema();
        
        KorisnikSistema clanPKLS =  nalogInputModel.getKorisnikSistema();
        
        ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = clanPKLSCMISKlijent.azurirajKorisnika(clanPKLS);
        if(!Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	System.out.println("Uspjesna izmjena.");
        	//loggovati header-e
        }else {
        	
        	System.out.println(odgovor.getStatusInfo().getStatusCode() + " " + odgovor.getStatusInfo().getReasonPhrase() );
        	for(Entry<String,List<Object>> entry : odgovor.getHeaders().entrySet()) {
        		System.out.print(entry.getKey() + " ");
        		for(Object objekat : entry.getValue())
        			System.out.print(objekat +" ");
        		System.out.println();
        	}
        }
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledClanovaPKLS.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(root));
        
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Еѕelite da napustite izmjenu naloga clana PKLS-a?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledClanovaPKLS.fxml"))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        KorisnikInputModel account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        imeIzmjena.setText(account.getIme());
        prezimeIzmjena.setText(account.getPrezime());
        usernameIzmjena.setText(account.getKorisnickoIme());
    }
}
