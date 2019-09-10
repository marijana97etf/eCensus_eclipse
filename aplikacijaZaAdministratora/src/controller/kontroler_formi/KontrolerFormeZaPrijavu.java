package controller.kontroler_formi;

import controller.KontrolerZaJezikeIPisma.KontrolerZaJezik;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.jshell.spi.ExecutionControl;
import model.korisnicki_nalozi.*;
import test.Pokreni_GUI_Aplikaciju;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class KontrolerFormeZaPrijavu implements Initializable {

    private Stage currentStage;
    private static KorisnikSistema currentAccount;

    private SkladisteNaloga nalozi;

    public static KorisnikSistema getCurrentAccount() {
        return currentAccount;
    }

    static KontrolerZaJezik kontrolerZaJezik = new KontrolerZaJezik();
    @FXML
    TextField username;
    @FXML
    PasswordField password;

    public void login(ActionEvent actionEvent) throws IOException, ExecutionControl.NotImplementedException {

        String usernameInput = username.getText();

        KorisnikSistema korisnikSistema = nalozi.stream().filter(e-> e.getKorisnickoIme().equals(usernameInput)).findFirst().get();
        if(korisnikSistema==null)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            String nepostojeciNalog = "Nepostojeci nalog, ispravno upišite podatke o svom nalogu.";
            alert.setContentText(nepostojeciNalog + System.lineSeparator() +
                    kontrolerZaJezik.latinToCyrillic(nepostojeciNalog));
            alert.showAndWait();
            return;
        }
        if(KorisnikSistema.napraviHesLozinke(password.getText()).equals(korisnikSistema.getLozinkaHash()))
        {
            currentAccount = korisnikSistema;
            if(korisnikSistema instanceof AdministratorAgencije)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                var uspjesnaPrijavaAdminAgencije = "Uspješno ste se prijavili kao administrator agencije.";
                alert.setContentText(uspjesnaPrijavaAdminAgencije + System.lineSeparator() +
                        kontrolerZaJezik.latinToCyrillic(uspjesnaPrijavaAdminAgencije));
                ButtonType buttonType = alert.showAndWait().get();
                if(!buttonType.getText().equals("OK")) return;
                Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"));
                currentStage.setScene(new Scene(root));
            }
            else if(korisnikSistema instanceof ClanPKLS)
            {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Uspješno ste se prijavili kao clan PKLS.");
                ButtonType buttonType = alert.showAndWait().get();
                if(!buttonType.getText().equals("OK")) return;
                Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"));
                currentStage.setScene(new Scene(root));
            }
            else if(korisnikSistema instanceof DEInstruktor)
            {
                throw new ExecutionControl.NotImplementedException("DEInstruktor - not implemented");
            }
            else if(korisnikSistema instanceof OGInstruktor)
            {
                throw new ExecutionControl.NotImplementedException("OGInstruktor - not implemented");
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Tip naloga nije validan!");
                alert.showAndWait();
                return;
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Lozinka nije ispravna. Pokušajte ponovo.");
            alert.showAndWait();
            return;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nalozi = Pokreni_GUI_Aplikaciju.getKontrolerZaCuvanjeNaloga().getSkladisteNaloga();
        
        System.out.println("nalozi: ");
        nalozi.stream().forEach(nalog -> System.out.println(nalog));
        System.out.println("to su nalozi");
        
        currentStage = Pokreni_GUI_Aplikaciju.getStage();
    }
}
