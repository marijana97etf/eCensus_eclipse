package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Aplikacija;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class KontrolerFormeZaRadAdministratoraAgencije implements Initializable {

    private KorisnikSistema korisnikSistema;

    @FXML
    Label labelaSaImenom;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        korisnikSistema = KontrolerFormeZaPrijavu.getTrenutniKorisnik();
        var wrapper = new Object()
        {
            String sadrzajLabele;
            String prezimeIIme = korisnikSistema.getPrezime() + " " + korisnikSistema.getIme();
        };
        Platform.runLater(()->
        {
            wrapper.sadrzajLabele=labelaSaImenom.getText();
            labelaSaImenom.setText(wrapper.sadrzajLabele + wrapper.prezimeIIme);
        });
    }

    public void odjaviSe(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void registrujClanaPKLS(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuClanaPKLS.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void pregledajNalogeClanovaPKLS(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaClanovaPKLS.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void pregledajNalogeDEInstruktora(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void registrujDEInstruktora(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuDEInstruktora.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void promjeniJezik(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaOdabirJezika.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void pregledajNalogePopisivaca(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
}
