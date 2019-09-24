package controller.kontroler_formi;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.logging.Level;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Aplikacija;
import util.OpstineCollection;

public class KontrolerFormeZaRegistracijuClanaPKLS implements Initializable {

	static int i=20;
	public void back(ActionEvent actionEvent) throws IOException {
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"))));
    }

    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    TextField jmbg;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    
    @FXML
    ChoiceBox<String> gradovi;

    public void registruj(ActionEvent actionEvent) throws IOException {
        List<TextInputControl> list = Arrays.asList(new TextInputControl[]{ ime, prezime, jmbg, username, password });
        if(list.stream().anyMatch(e-> e.getText().equals("")))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Unesite sve podatke u polja!");
            alert.showAndWait();
            return;
        }
        if(password.getText().length()<8)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Unesite 'jaču' lozinku!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Uspješno ste registrovali clana PKLS-a");
        KorisnikSistema clanPKLS = new ClanPKLS (
        		i++,
                ime.getText(),
                prezime.getText(),
                username.getText(),
                KorisnikSistema.napraviHesLozinke(password.getText()),
    			null,//JEZIK dodati, inace greska na serveru
    			null,//PISMO dodati inace greska na serveru
    			gradovi.getValue(), 
    			gradovi.getValue(), 
    			null, 
    			null, 
    			null, 
    			null);

        ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = clanPKLSCMISKlijent.registrujKorisnika(clanPKLS);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspjesna registracija.");
        }else {
        	
        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }
        
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"));
        Aplikacija.getStage().setScene(new Scene(root));
    }

    @FXML
    Label labelaZaIme;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getTrenutniKorisnik();
        var wrapper = new Object()
        {
            String sadrzajLabele;
            String prezimeIIme = korisnikSistema.getPrezime() + " " + korisnikSistema.getIme();
        };
        Platform.runLater(()->
        {
            wrapper.sadrzajLabele=labelaZaIme.getText();
            labelaZaIme.setText(wrapper.sadrzajLabele + wrapper.prezimeIIme);
        });
        gradovi.getItems().addAll(OpstineCollection.getOpstine());
        gradovi.setValue("Banja Luka");
    }
}
