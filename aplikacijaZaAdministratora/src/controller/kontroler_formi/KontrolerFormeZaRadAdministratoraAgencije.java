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

public class KontrolerFormeZaRadAdministratoraAgencije implements Initializable {

    private KorisnikSistema korisnikSistema;

    public void Logout(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPrijavu.fxml"))));
    }

    public void registrujClanaPKLS(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuClanaPKLS.fxml"))));
    }

    public void pregledajNalogeClanovaPKLS(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledClanovaPKLS.fxml"))));
    }


    @FXML
    Label labelaSaImenom;

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

    public void pregledajNalogeDEInstruktora(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"))));
    }

    public void registrujDEInstruktora(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRegistracijuDEInstruktora.fxml"))));
    }

    public void promjeniJezik(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPromjenuJezika.fxml"))));
    }

    public void promjeniPismo(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPromjenuPisma.fxml"))));
    }
}
