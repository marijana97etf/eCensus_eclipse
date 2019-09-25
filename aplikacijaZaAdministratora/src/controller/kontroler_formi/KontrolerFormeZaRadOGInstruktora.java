package controller.kontroler_formi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Aplikacija;

public class KontrolerFormeZaRadOGInstruktora implements Initializable{

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

    public void pregledajStatistickePodatke(ActionEvent actionEvent) {
        // TODO: Statistički podaci
    }

    public void pregledajNalogePopisivaca(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
    public void pregledajAktivnostiPopisivaca(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledAktivnostiPopisivaca.fxml"))));
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

    public void odjaviSe(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
}
