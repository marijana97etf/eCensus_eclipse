package controller.kontroler_formi;

import eCensus.rest.client.PopisivacCMISKlijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.korisnicki_nalozi.KorisnikSistema;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class KontrolerFormeZaIzmjenuNalogaPopisivaca implements Initializable {
    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    Label username;
    @FXML
    TextField newPassword;
    @FXML
    Label newPasswordLabel;
    @FXML
    CheckBox passwordCheckBox;

    public void izmjeni(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(Aplikacija.prevediRecenicu("Da li želite da sačuvate izmjene naloga popisivača?"));
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        
        var nalogInputModel = KontrolerFormeZaPregledNalogaClanovaPKLS.getAccountForEdit();
        KorisnikSistema popisivac =  nalogInputModel.getKorisnikSistema();

        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}";
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
            if (!newPassword.getText().matches(pattern)) {
                Alert pwdAgain2 = new Alert(Alert.AlertType.WARNING);
                pwdAgain2.setContentText(Aplikacija.prevediRecenicu("Unesite 'jaču' lozinku."));
                pwdAgain2.showAndWait();
                return;
            }
            popisivac.setLozinkaHash(KorisnikSistema.napraviHesLozinke(newPassword.getText())); //Ovako za svaku izmjenu
        }
        nalogInputModel.setPrezime(prezime.getText());
        nalogInputModel.setIme(ime.getText());
        nalogInputModel.updateKorisnikSistema();

        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = popisivacCMISKlijent.azurirajKorisnika(popisivac);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspjesna izmjena.");
        }else {
        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        Alert poruka = new Alert(Alert.AlertType.CONFIRMATION);
        poruka.setContentText(Aplikacija.prevediRecenicu("Uspješno ste izmjenili nalog popisivača!"));
        ButtonType tip = poruka.showAndWait().get();
        if(!tip.getText().equals("OK")) return;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"));
        } catch (IOException ex) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Neuspješno učitavanje forme.");
        }
        Aplikacija.getStage().setScene(new Scene(root));
        
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(Aplikacija.prevediRecenicu("Da li želite da napustite izmjenu naloga popisivača?"));
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        KorisnikInputModel account = KontrolerFormeZaPregledNalogaClanovaPKLS.getAccountForEdit();
        ime.setText(account.getIme());
        prezime.setText(account.getPrezime());
        username.setText(account.getKorisnickoIme());
        
        newPasswordLabel.setVisible(false);
        newPassword.setVisible(false);
    }

    public void promjenaSifre(ActionEvent actionEvent) {
        if(passwordCheckBox.isSelected())
        {
            newPasswordLabel.setVisible(true);
            newPassword.setVisible(true);
            passwordCheckBox.setVisible(false);
        }
    }
}
