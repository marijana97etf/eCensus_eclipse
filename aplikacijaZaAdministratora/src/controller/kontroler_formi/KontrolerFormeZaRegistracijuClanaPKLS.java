package controller.kontroler_formi;

import eCensus.rest.client.ClanPKLSCMISKlijent;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Aplikacija;
import util.GradoviCollection;
import util.OpstineCollection;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class KontrolerFormeZaRegistracijuClanaPKLS implements Initializable {
    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    TextField username;
    @FXML
    PasswordField password;
    @FXML
    ChoiceBox<String> gradoviChoiceBox;
    @FXML
    ChoiceBox<String> opstineChoiceBox;

    public void registruj(ActionEvent actionEvent) {
        List<TextInputControl> list = Arrays.asList(new TextInputControl[]{ ime, prezime, username, password });
        if(list.stream().anyMatch(e-> e.getText().equals("")))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Unesite sve podatke u polja!");
            alert.showAndWait();
            return;
        }
        if(!ime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Uneseno ime nije ispravno!");
            alert.showAndWait();
            return;
        }
        if(!prezime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Uneseno prezime nije ispravno!");
            alert.showAndWait();
            return;
        }
        if(!username.getText().matches("^[a-zA-Z0-9._-]{3,}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Uneseno korisničko ime nije ispravno!");
            alert.showAndWait();
            return;
        }
        if(!password.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Lozinka (8+ karaktera) mora sadržavati brojeve, mala i velika slova!");
            alert.showAndWait();
            return;
        }

        KorisnikSistema clanPKLS = new ClanPKLS (
                ime.getText(),
                prezime.getText(),
                username.getText(),
                KorisnikSistema.napraviHesLozinke(password.getText()),
    			gradoviChoiceBox.getValue(),
    			opstineChoiceBox.getValue());

        ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = clanPKLSCMISKlijent.registrujKorisnika(clanPKLS);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspjesna registracija.");
        }else {

        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Uspješno ste registrovali clana PKLS-a");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratoraAgencije.fxml"));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
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
        gradoviChoiceBox.getItems().addAll(GradoviCollection.getGradovi());
        gradoviChoiceBox.setValue("Banja Luka");
        opstineChoiceBox.getItems().addAll(OpstineCollection.getOpstine());
        opstineChoiceBox.setValue("Banja Luka");
    }

    public void back(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratoraAgencije.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Neuspjesno otvaranje forme.");
        }
    }
}
