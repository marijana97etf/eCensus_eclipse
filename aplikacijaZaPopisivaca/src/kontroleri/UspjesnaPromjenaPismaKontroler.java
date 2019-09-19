package kontroleri;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class UspjesnaPromjenaPismaKontroler {
	
	@FXML
	private Button closeButton;
	
	@FXML
	private void zatvoriAplikaciju(ActionEvent event) {
		Stage stage = (Stage) closeButton.getScene().getWindow();
	    stage.close();
	}
}
