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

    public void Logout(ActionEvent actionEvent) throws IOException {
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"))));
    }

    public void promjeniJezik(ActionEvent actionEvent) throws IOException {
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPromjenuJezika.fxml"))));
    }
    
    public void promjeniPismo(ActionEvent actionEvent) throws IOException {
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPromjenuPisma.fxml"))));
    }
    
    public void registrujOGInstruktora(ActionEvent actionEvent)
    {
    	try {
			Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuOGInstruktora.fxml"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void pregledajNalogeOGInstruktora(ActionEvent actionEvent)
    {
    	try {
			Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaOGInstruktora.fxml"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void registrujPopisivaca(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuPopisivaca.fxml"))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void pregledajNalogePopisivaca(ActionEvent actionEvent)
    {
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
