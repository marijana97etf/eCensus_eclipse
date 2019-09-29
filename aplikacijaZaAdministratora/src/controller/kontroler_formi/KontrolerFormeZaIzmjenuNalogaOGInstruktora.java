package controller.kontroler_formi;

import eCensus.rest.client.OGInstruktorCMISKLijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;
import util.GradoviCollection;
import util.OpstineCollection;
import util.PromjenaPisma;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class KontrolerFormeZaIzmjenuNalogaOGInstruktora implements Initializable {
    @FXML
    ChoiceBox<String> gradoviComboBox;
    @FXML
    ChoiceBox<String> opstineComboBox;
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
    CheckBox newPasswordCheckBox;


    public void izmjeni(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(Aplikacija.prevediRecenicu("Da li želite da sačuvate izmjene naloga opštinkog/gradskog instruktora?"));
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;

        var nalogInputModel = KontrolerFormeZaPregledNalogaClanovaPKLS.getAccountForEdit();
        OGInstruktor ogInstruktor = (OGInstruktor) nalogInputModel.getKorisnikSistema();

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
        if(newPasswordCheckBox.isSelected())
        {
            if(!newPassword.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}"))
            {
                Alert pwdAgain2 = new Alert(Alert.AlertType.WARNING);
                pwdAgain2.setContentText(Aplikacija.prevediRecenicu("Lozinka sadrži brojeve, mala i velika slova"));
                pwdAgain2.showAndWait();
                return;
            }
            ogInstruktor.setLozinkaHash(KorisnikSistema.napraviHesLozinke(newPassword.getText())); //Ovako za svaku izmjenu
        }
        nalogInputModel.setPrezime(prezime.getText());
        nalogInputModel.setIme(ime.getText());
        ogInstruktor.setGrad(PromjenaPisma.zamijeniCirilicuLatinicom(gradoviComboBox.getValue()));
        ogInstruktor.setOpstina(PromjenaPisma.zamijeniCirilicuLatinicom(opstineComboBox.getValue()));
        nalogInputModel.updateKorisnikSistema();

        OGInstruktorCMISKLijent ogInstruktorCMISKlijent = new OGInstruktorCMISKLijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = ogInstruktorCMISKlijent.azurirajKorisnika(ogInstruktor);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspješna izmjena.");
        }else {
            Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        Alert poruka = new Alert(Alert.AlertType.CONFIRMATION);
        poruka.setContentText(Aplikacija.prevediRecenicu("Uspješno ste izmjenili nalog opštinkog/gradskog instruktora!"));
        ButtonType tip = poruka.showAndWait().get();
        if(!tip.getText().equals("OK")) return;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaOGInstruktora.fxml"));
        } catch (IOException ex) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Neuspješno učitavanje forme.");
        }
        Aplikacija.getStage().setScene(new Scene(root));

    }

    public void povratak(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText(Aplikacija.prevediRecenicu("Da li želite da napustite izmjenu naloga opštinkih/gradskih instruktora?"));
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaOGInstruktora.fxml"));
        } catch (IOException ex) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Neuspješno učitavanje forme.");
        }
        Aplikacija.getStage().setScene(new Scene(root));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        newPasswordLabel.setVisible(false);
        newPassword.setVisible(false);

        KorisnikInputModel account = KontrolerFormeZaPregledNalogaClanovaPKLS.getAccountForEdit();
        ime.setText(account.getIme());
        prezime.setText(account.getPrezime());
        username.setText(account.getKorisnickoIme());

        var korisnik = (OGInstruktor)account.getKorisnikSistema();

    	gradoviComboBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(GradoviCollection.getGradovi())));
        String grad = korisnik.getGrad();
        if(grad!=null)
            gradoviComboBox.setValue(Aplikacija.prevediRecenicu(grad));

        opstineComboBox.getItems().addAll(Aplikacija.prevediRecenice(new ArrayList<>(OpstineCollection.getOpstine())));
        String opstina = korisnik.getOpstina();
        if(opstina!=null)
            gradoviComboBox.setValue(Aplikacija.prevediRecenicu(opstina));
    }

    public void promjenaSifre(ActionEvent actionEvent) {
        if(newPasswordCheckBox.isSelected())
        {
            newPasswordLabel.setVisible(true);
            newPassword.setVisible(true);
            newPasswordCheckBox.setVisible(false);
        }
    }
}
