package kontroleri;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UspjesnaRegistracijaAdministratoraAgencijeKontroler {
	
	@FXML
	private Button OKButton;

	@FXML
	private void OKButtonAction() {
		Stage stage = (Stage) OKButton.getScene().getWindow();
	    stage.close();
	    KontrolerFormeZaRadPowerUsera.registracijaAdministratoraAgencijeStage.close();
	}
}
