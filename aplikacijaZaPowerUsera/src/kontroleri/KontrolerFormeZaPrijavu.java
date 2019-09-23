package kontroleri;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;
import util.PrikazObavjestenja;

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
        		PrikazObavjestenja.prikaziUpozorenje("Morate unijeti korisničko ime i lozinku.");
        	else
        		PrikazObavjestenja.prikaziUpozorenje("Морате унијети корисничко име и лозинку.");
        }
        else{
            //Ako je autentikacija ok:
        	try {
	        	Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRadPowerUsera.fxml"));
	            Main.primaryStage.setScene(new Scene(root));
        	}
        	catch(IOException e) {
        		e.printStackTrace();
        	}
        }

    }
}
