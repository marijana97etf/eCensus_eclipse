package kontroleri;

import java.io.ByteArrayInputStream;
import java.util.Map.Entry;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class KontrolerMapePopisnogKruga {
	@FXML
	private ImageView imageView;
	
	@FXML
	private GridPane gridPane;

	@FXML
	private void prikaziSlikuPopisnogKruga() {
		byte[] slika;
		Stage stage = (Stage)imageView.getScene().getWindow();
		for(Entry<PopisniKrug, Stage> entry : KontrolerFormeZaPrikazMapePopisnogKruga.util.entrySet()) {
			if(stage.equals(entry.getValue())) {
				slika = entry.getKey().getSlikaBytes();
				imageView.setImage(new Image(new ByteArrayInputStream(slika)));
			}
		}
	}
}
