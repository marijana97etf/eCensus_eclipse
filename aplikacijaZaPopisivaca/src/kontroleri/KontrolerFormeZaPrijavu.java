package kontroleri;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;
import util.HashEngine;

import java.io.File;
import java.io.IOException;

public class KontrolerFormeZaPrijavu {
    @FXML
    private TextField UnosKorisnickogImenaField;
    @FXML
    private PasswordField UnosLozinkeField;

    public void prijavaNaSistemButtonAction() {
        String korisnickoIme = UnosKorisnickogImenaField.getText();
        String lozinka = UnosLozinkeField.getText();

        if(korisnickoIme.isEmpty() || lozinka.isEmpty()) {
        	if(!"српски".equals(Main.trenutniJezik))
        		prikaziUpozorenje("Morate unijeti korisničko ime i lozinku.");
        	else
        		prikaziUpozorenje("Морате унијети корисничко име и лозинку.");
        }
        else{
            try {
                String hashLozinke = HashEngine.hashValue(lozinka);
                //TODO: poslati zahtjev cmis-u da se provjere podaci za prijavu.
                //Ako je sve okej:
                Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRadPopisivaca.fxml"));
                Main.primaryStage.setScene(new Scene(root));
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    private void prikaziUpozorenje(String poruka){
    	String greska = "Greška";
    	if("српски".equals(Main.trenutniJezik))
    		greska = "Грешка";
    	
        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
        userNotSelectedAlert.setTitle("greska");
        userNotSelectedAlert.setHeaderText(greska + "!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }
}
