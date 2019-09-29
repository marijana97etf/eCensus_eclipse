package controller.kontroler_formi;

import eCensus.rest.client.DEInstruktorCMISKlijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.DEInstruktor.ENTITET;
import model.korisnicki_nalozi.KorisnikSistema;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;
import util.PromjenaPisma;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;

public class KontrolerFormeZaIzmjenuNalogaDEInstruktora implements Initializable {

    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    Label username;
    @FXML
    TextField newPassword;
    @FXML
    ChoiceBox<String> entitetChoiceBox;
    @FXML
    CheckBox passwordCheckBox;

    public void izmjeni(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(Aplikacija.prevediRecenicu("Da li želite da sačuvate izmjene naloga državnog/entitetskog instruktora?"));
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        
        var account = KontrolerFormeZaPregledNalogaClanovaPKLS.getAccountForEdit();
        DEInstruktor deInstruktor = (DEInstruktor) account.getKorisnikSistema();

        if(!ime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(Aplikacija.prevediRecenicu("Uneseno ime nije ispravno!"));
            alert2.showAndWait();
            return;
        }
        if(!prezime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText(Aplikacija.prevediRecenicu("Uneseno prezime nije ispravno!"));
            alert2.showAndWait();
            return;
        }
        if(passwordCheckBox.isSelected())
        {
            if(!newPassword.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}"))
            {
                Alert pwdAgain2 = new Alert(Alert.AlertType.WARNING);
                pwdAgain2.setContentText(Aplikacija.prevediRecenicu("Lozinka sadrži brojeve, mala i velika slova!"));
                pwdAgain2.showAndWait();
                return;
            }
            deInstruktor.setLozinkaHash(KorisnikSistema.napraviHesLozinke(newPassword.getText())); //Ovako za svaku izmjenu
        }
        account.setPrezime(prezime.getText());
        account.setIme(ime.getText());
        deInstruktor.setEntitet(DEInstruktor.ENTITET.getENTITET(PromjenaPisma.zamijeniCirilicuLatinicom(entitetChoiceBox.getValue())));
        account.updateKorisnikSistema();

        DEInstruktorCMISKlijent deInstuktorCMISKlijent = new DEInstruktorCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = deInstuktorCMISKlijent.azurirajKorisnika(deInstruktor);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspješna izmjena naloga državno/entitetskog instruktora.");
        }else {

        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        Alert poruka = new Alert(Alert.AlertType.CONFIRMATION);
        poruka.setContentText(Aplikacija.prevediRecenicu("Uspješno ste izmjenili nalog državnog/entitetskog instruktora!"));
        ButtonType tip = poruka.showAndWait().get();
        if(!tip.getText().equals("OK")) return;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"));
        } catch (IOException ex) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Neuspješno učitavanje forme.");
        }
        Aplikacija.getStage().setScene(new Scene(root));
        
    }

    public void povratak(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(Aplikacija.prevediRecenicu("Da li želite da napustite izmjenu naloga državnog/entitetskog instruktora?"));
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        try {
            Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaDEInstruktora.fxml"))));
        } catch (IOException e) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Neuspješno učitavanje forme.");
        }
    }

    @FXML
    Label newPasswordLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newPasswordLabel.setVisible(false);
        newPassword.setVisible(false);

        KorisnikInputModel account = KontrolerFormeZaPregledNalogaClanovaPKLS.getAccountForEdit();
        ime.setText(account.getIme());
        prezime.setText(account.getPrezime());
        username.setText(account.getKorisnickoIme());

        entitetChoiceBox.getItems().addAll(Aplikacija.prevediRecenice(Arrays.asList("Bosna i Hercegovina", "Federacija Bosne i Hercegovine", "Republika Srpska")));
        DEInstruktor korisnik = (DEInstruktor)account.getKorisnikSistema();
        ENTITET value = korisnik.getEntitet();
        if(value!=null)
        	entitetChoiceBox.setValue(Aplikacija.prevediRecenicu(korisnik.getEntitet().getValue()));
    }

    public void promjeniLozinku(ActionEvent actionEvent) {
        if(passwordCheckBox.isSelected())
        {
            newPasswordLabel.setVisible(true);
            newPassword.setVisible(true);
            passwordCheckBox.setVisible(false);
        }
    }
}
