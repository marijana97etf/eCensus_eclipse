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

public class KontrolerFormeZaRadClanaPKLS implements Initializable {

    private KorisnikSistema korisnikSistema;

    @FXML
    private Label labelaSaImenom;

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

    public void promjeniJezik(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaOdabirJezika.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void registrujOGInstruktora(ActionEvent actionEvent)
    {
    	try {
			Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuOGInstruktora.fxml"))));
		} catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
    
    public void pregledajNalogeOGInstruktora(ActionEvent actionEvent)
    {
    	try {
			Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaOGInstruktora.fxml"))));
		} catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void registrujPopisivaca(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuPopisivaca.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void pregledajNalogePopisivaca(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void odjaviSe(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void pregledajStatistickePodatke(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledStatistickihPodataka.fxml"))));
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

    public void dodajPopisneKrugove(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaDodavanjePopisnihKrugova.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }

    public void dodjeliPopisneKrugovePopisivacu(ActionEvent actionEvent) {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaDodjeluPopisnihKrugovaPopisivacu.fxml"))));
        } catch (IOException e) {
        	e.printStackTrace();
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno čitanje forme.");
        }
    }
}
