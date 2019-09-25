package controller.kontroler_formi;

import eCensus.rest.client.ClanPKLSCMISKlijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.KorisnikSistema;
import model.table_input_models.KorisnikInputModel;
import test.Aplikacija;
import util.GradoviCollection;
import util.OpstineCollection;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class KontrolerFormeZaIzmjenuNalogaClanaPKLS implements Initializable {
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Da li želite da sačuvate izmjene naloga člana PKLS?");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;

        var nalogInputModel = KontrolerFormeZaPregledNalogaClanovaPKLS.getAccountForEdit();
        ClanPKLS clanPKLS = (ClanPKLS) nalogInputModel.getKorisnikSistema();

        if(!ime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Uneseno ime nije ispravno!");
            alert2.showAndWait();
            return;
        }
        if(!prezime.getText().matches("^[a-zA-Z- ]{2,}$"))
        {
            Alert alert2 = new Alert(Alert.AlertType.ERROR);
            alert2.setContentText("Uneseno prezime nije ispravno!");
            alert2.showAndWait();
            return;
        }
        if(newPasswordCheckBox.isSelected())
        {
            if(!newPassword.getText().matches("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}"))
            {
                Alert pwdAgain2 = new Alert(Alert.AlertType.ERROR);
                pwdAgain2.setContentText("Lozinka sadrži brojeve, mala i velika slova");
                pwdAgain2.showAndWait();
                return;
            }
            clanPKLS.setLozinkaHash(KorisnikSistema.napraviHesLozinke(newPassword.getText())); //Ovako za svaku izmjenu
        }
        nalogInputModel.setPrezime(prezime.getText());
        nalogInputModel.setIme(ime.getText());
        clanPKLS.setGrad(gradoviComboBox.getValue());
        clanPKLS.setOpstina(opstineComboBox.getValue());
        nalogInputModel.updateKorisnikSistema();

        ClanPKLSCMISKlijent clanPKLSCMISKlijent = new ClanPKLSCMISKlijent(KontrolerFormeZaPrijavu.getTrenutniKorisnik());
        Response odgovor = clanPKLSCMISKlijent.azurirajKorisnika(clanPKLS);
        if(Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
            Aplikacija.connLogger.getLogger().log(Level.INFO, "Uspješna izmjena.");
        }else {
            Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
        }

        Alert poruka = new Alert(Alert.AlertType.CONFIRMATION);
        poruka.setContentText("Uspješno ste izmjenili nalog člana PKLS!");
        ButtonType tip = poruka.showAndWait().get();
        if(!tip.getText().equals("OK")) return;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaClanovaPKLS.fxml"));
        } catch (IOException ex) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno učitavanje forme.");
        }
        Aplikacija.getStage().setScene(new Scene(root));

    }

    public void povratak(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Da li želite da napustite izmjenu naloga člana PKLS");
        ButtonType buttonType = alert.showAndWait().get();
        if(!buttonType.getText().equals("OK")) return;
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/FormaZaPregledNalogaClanovaPKLS.fxml"));
        } catch (IOException ex) {
            Aplikacija.connLogger.getLogger().log(Level.WARNING, "Neuspješno učitavanje forme.");
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

        var korisnik = (ClanPKLS)account.getKorisnikSistema();

    	gradoviComboBox.getItems().addAll(GradoviCollection.getGradovi());
        String grad = korisnik.getGrad();
        if(grad!=null)
            gradoviComboBox.setValue(grad);

        opstineComboBox.getItems().addAll(OpstineCollection.getOpstine());
        String opstina = korisnik.getOpstina();
        if(opstina!=null)
            gradoviComboBox.setValue(opstina);
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
