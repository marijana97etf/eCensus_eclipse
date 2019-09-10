package controller.kontroler_formi;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import test.Pokreni_GUI_Aplikaciju;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class KontrolerFormeZaRegistracijuDEInstruktora implements Initializable {
    public void back(ActionEvent actionEvent) throws IOException {
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"))));
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
    ChoiceBox choiceBox2;

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
        alert.setContentText("Uspješno ste registrovali državnog/entitetskog instruktora");
        DEInstruktor.DRZAVA_ENTITET drzava_entitet = DEInstruktor.StringTODrzavaEntitet((String)choiceBox2.getValue());
        KorisnikSistema deInstruktor = new DEInstruktor (
                jmbg.getText(),
                ime.getText(),
                prezime.getText(),
                username.getText(),
                password.getText(),
                drzava_entitet,
                null, null);

        Pokreni_GUI_Aplikaciju.getKontrolerZaCuvanjeNaloga().getSkladisteNaloga().add(deInstruktor);
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"));
        Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(root));
    }

    @FXML
    Label labelaZaIme;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getCurrentAccount();
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
        choiceBox2.getItems().addAll("Bosna i Hercegovina", "Federacija Bosne i Hercegovine", "Republika Srpska");
        choiceBox2.setValue(choiceBox2.getItems().get(0));
    }
}
