package controller.kontroler_formi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import jdk.jshell.spi.ExecutionControl;
import model.korisnicki_nalozi.*;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;
import test.Pokreni_GUI_Aplikaciju;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KontrolerFormeZaPromjenuPisma implements Initializable {
    @FXML
    Label label;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getCurrentAccount();
        String newLine = System.lineSeparator();
        if(korisnikSistema instanceof AdministratorAgencije)
        {
            label.setText("Administrator agencije :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else if(korisnikSistema instanceof ClanPKLS)
        {
            label.setText("Clan PKLS :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else if(korisnikSistema instanceof DEInstruktor)
        {
            label.setText("Drzavni/entitetski instruktor :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else if(korisnikSistema instanceof OGInstruktor) {
            label.setText("Opštinski/gradski instruktor :" + newLine + korisnikSistema.getPrezime() + " " + korisnikSistema.getIme());
        }
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Nalog nije ok!");
            ButtonType buttonType2 = alert2.showAndWait().get();
        }
    }

    @FXML
    RadioButton cirilica;

    @FXML
    RadioButton latinica;


    public void promjeniPismo(ActionEvent actionEvent) throws IOException, ExecutionControl.NotImplementedException {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getCurrentAccount();
        if(cirilica.isSelected())
            korisnikSistema.setPismo(PISMO.CIRILICA);
        else if(latinica.isSelected())
            korisnikSistema.setPismo(PISMO.LATINICA);
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Odaberite pismo.");
            ButtonType buttonType = alert.showAndWait().get();
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Da li želite da promjenite pismo?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        if(korisnikSistema instanceof AdministratorAgencije)
        {
            Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"))));
        }
        else if(korisnikSistema instanceof ClanPKLS)
        {
            Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"))));
        }
        else if(korisnikSistema instanceof DEInstruktor) throw new ExecutionControl.NotImplementedException("DEInstruktor");
        else if(korisnikSistema instanceof OGInstruktor) throw new ExecutionControl.NotImplementedException("OGInstruktor");
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Nalog nije ok!");
            ButtonType buttonType2 = alert2.showAndWait().get();
        }
    }

    public void back(ActionEvent actionEvent) throws IOException, ExecutionControl.NotImplementedException {
        KorisnikSistema korisnikSistema = KontrolerFormeZaPrijavu.getCurrentAccount();
        if(korisnikSistema instanceof AdministratorAgencije)
        {
            Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"))));
        }
        else if(korisnikSistema instanceof ClanPKLS)
        {
            Pokreni_GUI_Aplikaciju.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"))));
        }
        else if(korisnikSistema instanceof DEInstruktor) throw new ExecutionControl.NotImplementedException("DEInstruktor");
        else if(korisnikSistema instanceof OGInstruktor) throw new ExecutionControl.NotImplementedException("OGInstruktor");
        else
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Nalog nije ok!");
            ButtonType buttonType2 = alert2.showAndWait().get();
        }
    }
}
