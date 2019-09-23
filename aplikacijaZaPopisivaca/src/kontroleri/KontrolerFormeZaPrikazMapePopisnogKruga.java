package kontroleri;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class KontrolerFormeZaPrikazMapePopisnogKruga {
	@FXML
	private ListView<String> listaUlica;
	
	public KontrolerFormeZaPrikazMapePopisnogKruga() {
		KontrolerFormeZaRadPopisivaca.pregledMapePopisnogKrugaStage.setOnShowing((event) -> inicijalizujListuUlica());
	}
	
	@FXML
	private void pogledajMapuButtonAction() {
		
	}
	
	private void inicijalizujListuUlica() {
		
	}
}
