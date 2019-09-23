package kontroleri;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import util.PrikazObavjestenja;

public class KontrolerFormeZaRegistracijuAdministratoraAgencije {

	@FXML
	private TextField imeTextField;
	@FXML
	private TextField prezimeTextField;
	@FXML
	private TextField jmbgTextField;
	@FXML
	private TextField korisnickoImeTextField;
	@FXML
	private TextField lozinkaTextField;

	@FXML
	private void registrujButtonAction() {
		String ime = imeTextField.getText();
		String prezime = prezimeTextField.getText();
		String jmbg = jmbgTextField.getText();
		String korisnickoIme = korisnickoImeTextField.getText();
		String lozinka = lozinkaTextField.getText();

		if(ime.isEmpty() || prezime.isEmpty() || jmbg.isEmpty() || korisnickoIme.isEmpty() || lozinka.isEmpty())
			PrikazObavjestenja.prikaziUpozorenje("Morate unijeti sve podatke.");
		else {
			//Poslati podatke na cmis server

			try {
				Stage stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);

	            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "UspjesnaRegistracijaAdministratoraAgencije.fxml"));
	            stage.setScene(new Scene(root));
	            stage.setResizable(false);
	            stage.show();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

	}
}
