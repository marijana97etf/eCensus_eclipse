package kontroleri;

import java.io.File;
import java.io.IOException;

import eCensus.rest.client.PowerUserCMISKlijent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import main.Main;
import model.korisnicki_nalozi.KorisnikSistema;
import util.PrikazObavjestenja;

public class KontrolerFormeZaPromjenuLozinke {
	
	@FXML
	private PasswordField novaLozinkaField;
	@FXML
	private PasswordField novaLozinkaPonovoField;
	
	@FXML
	private void promijeniLozinkuButtonAction() {
		String novaLozinka = novaLozinkaField.getText();
		String novaLozinkaPonovo = novaLozinkaPonovoField.getText();
		
		if(novaLozinka.isEmpty() || novaLozinkaPonovo.isEmpty()) {
			if("српски".equals(Main.trenutniJezik))
				PrikazObavjestenja.prikaziUpozorenje("Morate popuniti polja.");
			else
				PrikazObavjestenja.prikaziUpozorenje("Морате попунити поља.");
		}
		else if(!novaLozinka.equals(novaLozinkaPonovo)) {
			if("српски".equals(Main.trenutniJezik))
				PrikazObavjestenja.prikaziUpozorenje("Lozinke moraju biti iste.");
			else
				PrikazObavjestenja.prikaziUpozorenje("Лозинке морају бити исте.");
		}
		else {
			PowerUserCMISKlijent klijent = new PowerUserCMISKlijent(KontrolerFormeZaPrijavu.korisnik);
			KontrolerFormeZaPrijavu.korisnik.setLozinkaHash(KorisnikSistema.napraviHesLozinke(novaLozinka));
			klijent.azurirajKorisnika(KontrolerFormeZaPrijavu.korisnik);
			KontrolerFormeZaPrijavu.promjenaLozinkeStage.close();
			Parent root;
			try {
				root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRadPowerUsera.fxml"));
				 Main.primaryStage.setScene(new Scene(root));
		         Main.primaryStage.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
           
		}
	}
}
