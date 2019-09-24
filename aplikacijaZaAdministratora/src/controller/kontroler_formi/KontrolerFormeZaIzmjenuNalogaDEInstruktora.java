package controller.kontroler_formi;

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
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.DEInstruktor.ENTITET;
import model.korisnicki_nalozi.KorisnikSistema;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.ws.rs.core.Response;

import eCensus.rest.client.ClanPKLSCMISKlijent;
import eCensus.rest.client.DEInstruktorCMISKlijent;

public class KontrolerFormeZaIzmjenuNalogaDEInstruktora implements Initializable {

    @FXML
    TextField imeIzmjena;

    @FXML
    TextField prezimeIzmjena;

    @FXML
    TextField usernameIzmjena;

    @FXML
    ChoiceBox<String> choiceBox;

    public void izmjeni(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Å¾elite da saÄ�uvate izmjene naloga drÅ¾avnog/entitetskog instruktora?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        
        var account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        account.setPrezime(prezimeIzmjena.getText());
        account.setIme(imeIzmjena.getText());
        account.setKorisnickoIme(usernameIzmjena.getText());
        ((DEInstruktor)account.getKorisnikSistema()).setEntitet(DEInstruktor.ENTITET.getENTITET((String) choiceBox.getValue()));
        account.updateKorisnikSistema();
        
        KorisnikSistema deInstuktor =  account.getKorisnikSistema();
        
        DEInstruktorCMISKlijent deInstuktorCMISKlijent = new DEInstruktorCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = deInstuktorCMISKlijent.azurirajKorisnika(deInstuktor);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspjesna izmjena naloga drÅ¾avno/entitetskog instruktora.");
        }else {
        	
        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Aplikacija.getStage().setScene(new Scene(root));
        
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Ã…Â¾elite da napustite izmjenu naloga drÃ…Â¾avnog/entitetskog instruktora?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        KorisnikInputModel account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        imeIzmjena.setText(account.getIme());
        prezimeIzmjena.setText(account.getPrezime());
        usernameIzmjena.setText(account.getKorisnickoIme());
        choiceBox.getItems().addAll("Bosna i Hercegovina", "Federacija Bosne i Hercegovine", "Republika Srpska");
        var korisnik = (DEInstruktor)account.getKorisnikSistema();
        ENTITET value = korisnik.getEntitet();
        if(value!=null)
        	choiceBox.setValue(korisnik.getEntitet().getValue());
    }
}
