package controller.kontroler_formi;

import eCensus.rest.client.ClanPKLSCMISKlijent;
import eCensus.rest.client.PopisivacCMISKlijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.korisnicki_nalozi.KorisnikSistema;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KontrolerFormeZaIzmjenuNalogaPopisivaca implements Initializable {
    @FXML
    TextField ime;
    @FXML
    TextField prezime;
    @FXML
    TextField username;
    @FXML
    TextField oldPassword;
    @FXML
    TextField newPassword;


    public void izmjeni(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Å¾elite da saÄ�uvate izmjene naloga popisivaÄ�a?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        
        var nalogInputModel = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        KorisnikSistema popisivac =  nalogInputModel.getKorisnikSistema();


        if(!KorisnikSistema.napraviHesLozinke(oldPassword.getText()).equals(popisivac.getLozinkaHash())) {
            Alert pwdAgain = new Alert(Alert.AlertType.WARNING);
            pwdAgain.setContentText("VaÅ¡a unesena stara lozinka nije ispravna.");
            pwdAgain.showAndWait();
            return;
        }
        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if(!newPassword.getText().matches(pattern))
        {
            Alert pwdAgain2 = new Alert(Alert.AlertType.WARNING);
            pwdAgain2.setContentText("Unesite 'jaÄ�u' lozinku.");
            pwdAgain2.showAndWait();
            return;
        }

        nalogInputModel.setPrezime(prezime.getText());
        nalogInputModel.setIme(ime.getText());
        //Pattern pattern = Pattern.compile("^[a-zA-Z0-9._-]{3,}$");
        //Matcher matcher = pattern.matcher(username.getText());
        popisivac.setLozinkaHash(KorisnikSistema.napraviHesLozinke(newPassword.getText())); //Ovako za svaku izmjenu
        nalogInputModel.updateKorisnikSistema();

        PopisivacCMISKlijent popisivacCMISKlijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = popisivacCMISKlijent.azurirajKorisnika(popisivac);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        	Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspjesna izmjena.");
        }else {
        	Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }
        
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Aplikacija.getStage().setScene(new Scene(root));
        
    }

    public void back(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Da li Å¾elite da napustite izmjenu naloga popisivaÄ�a?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Aplikacija.getStage().setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaPopisivaca.fxml"))));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        KorisnikInputModel account = KontrolerFormeZaPregledClanovaPKLS.getAccountForEdit();
        ime.setText(account.getIme());
        prezime.setText(account.getPrezime());
        username.setText(account.getKorisnickoIme());
    }
}
