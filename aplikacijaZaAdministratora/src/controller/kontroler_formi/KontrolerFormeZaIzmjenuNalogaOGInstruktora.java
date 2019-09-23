package controller.kontroler_formi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javax.ws.rs.core.Response;
import eCensus.rest.client.OGInstruktorCMISKLijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;
import util.OpstineCollection;

public class KontrolerFormeZaIzmjenuNalogaOGInstruktora implements Initializable {

    @FXML
    TextField imeIzmjena;

    @FXML
    TextField prezimeIzmjena;

    @FXML
    TextField usernameIzmjena;

	@FXML
    ChoiceBox<String> gradovi;
    
    public void izmjeni(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li želite da sačuvate izmjene naloga opštinskog/gradskog instruktora?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        
        var account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        KorisnikSistema ogInstruktor =  account.getKorisnikSistema();
        
        ((OGInstruktor)ogInstruktor).setGrad(gradovi.getValue());
        account.setPrezime(prezimeIzmjena.getText());
        account.setIme(imeIzmjena.getText());
        account.setKorisnickoIme(usernameIzmjena.getText());
        account.updateKorisnikSistema();
        
        OGInstruktorCMISKLijent clanPKLSCMISKlijent = new OGInstruktorCMISKLijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = clanPKLSCMISKlijent.azurirajKorisnika(ogInstruktor);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspjesna izmjena.");
        }else {
        	
        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaOGInstruktora.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Aplikacija.getStage().setScene(new Scene(root));
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li želite da napustite izmjenu naloga opštinskog/gradskog instruktora?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaOGInstruktora.fxml"))));
    }

	@Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        KorisnikInputModel account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        imeIzmjena.setText(account.getIme());
        prezimeIzmjena.setText(account.getPrezime());
        usernameIzmjena.setText(account.getKorisnickoIme());
        
        
    	gradovi.getItems().addAll(OpstineCollection.getOpstine());
        gradovi.setValue(((OGInstruktor)account.getKorisnikSistema()).getOpstina());
        var korisnik = (OGInstruktor)account.getKorisnikSistema();
        String value = korisnik.getGrad();
        if(value!=null)
        	gradovi.setValue(value);
        System.out.println(value);
    }
}
