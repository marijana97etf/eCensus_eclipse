package controller.kontroler_formi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class KontrolerFormeZaUspjesnuPromjenuJezikaIPisma {
	
	@FXML
	private Button closeButton;
	
	@FXML
	private void zatvoriAplikaciju(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}
