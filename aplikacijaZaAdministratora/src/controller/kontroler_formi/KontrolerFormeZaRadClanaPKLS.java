package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Pokreni_GUI_Aplikaciju;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KontrolerFormeZaRadClanaPKLS implements Initializable {

    private KorisnikSistema korisnikSistema;

    @FXML
    private Label labelaSaImenom;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        korisnikSistema = KontrolerFormeZaPrijavu.getCurrentAccount();
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
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"))));
    }

    public void promjeniJezik(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPromjenuJezika.fxml"))));
    }
}
