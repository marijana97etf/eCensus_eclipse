package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.DEInstruktor.DRZAVA;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Aplikacija;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javax.ws.rs.core.Response;

import eCensus.rest.client.DEInstruktorCMISKlijent;
import util.PromjenaPisma;

public class KontrolerFormeZaRegistracijuDEInstruktora implements Initializable {
    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    @FXML
    ChoiceBox<String> entitet;

    public void registruj(ActionEvent actionEvent) {
        List<TextInputControl> list = Arrays.asList(new TextInputControl[]{ ime, prezime, username, password });
        if(list.stream().anyMatch(e-> e.getText().equals("")))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Aplikacija.prevediRecenicu("Unesite sve podatke u polja!"));
            alert.showAndWait();
            return;
        }
        if(!ime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Aplikacija.prevediRecenicu("Uneseno ime nije ispravno!"));
            alert.showAndWait();
            return;
        }
        if(!prezime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Aplikacija.prevediRecenicu("Uneseno prezime nije ispravno!"));
            alert.showAndWait();
            return;
        }
        if(!username.getText().matches("^[a-zA-Z0-9._-]{3,}$"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(Aplikacija.prevediRecenicu("Uneseno korisničko ime nije ispravno!"));
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

        DEInstruktor.ENTITET entitet = DEInstruktor.ENTITET.getENTITET(PromjenaPisma.zamijeniCirilicuLatinicom(this.entitet.getValue()));
        KorisnikSistema deInstruktor = new DEInstruktor (
                ime.getText(),
                prezime.getText(),
                username.getText(),
                KorisnikSistema.napraviHesLozinke(password.getText()),
                DRZAVA.BIH,
                entitet);

        DEInstruktorCMISKlijent DEInstruktorCMISKlijent = new DEInstruktorCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = DEInstruktorCMISKlijent.registrujKorisnika(deInstruktor);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspješna registracija državno/entitetskog instruktora.");
        }else {

        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(Aplikacija.prevediRecenicu("Uspješno ste registrovali državnog/entitetskog instruktora"));
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
            labelaZaIme.setText(wrapper.sadrzajLabele + Aplikacija.prevediRecenicu(wrapper.prezimeIIme));
        });
        entitet.getItems().addAll(Aplikacija.prevediRecenice(Arrays.asList("Federacija Bosne i Hercegovine", "Republika Srpska", "Brčko Distrikt")));
        entitet.setValue(Aplikacija.prevediRecenicu(entitet.getItems().get(0)));
    }

    public void back(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratoraAgencije.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno učitavanje forme.");
        }
    }
}
