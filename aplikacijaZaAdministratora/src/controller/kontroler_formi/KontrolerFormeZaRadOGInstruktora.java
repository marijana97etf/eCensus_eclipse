package controller.kontroler_formi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    public void Logout(ActionEvent actionEvent) throws IOException {
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"))));
    }

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

    public void pregledajStatistickePodatke(ActionEvent actionEvent) throws IOException {
    }
    
    public void pregledajAktivnostiPopisivaca(ActionEvent actionEvent) throws IOException {
    }

    public void promjeniJezik(ActionEvent actionEvent) throws IOException {
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPromjenuJezika.fxml"))));
    }

    public void promjeniPismo(ActionEvent actionEvent) throws IOException {
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPromjenuPisma.fxml"))));
    }
	
}
