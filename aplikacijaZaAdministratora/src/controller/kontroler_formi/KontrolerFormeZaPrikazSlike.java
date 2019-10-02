package controller.kontroler_formi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class KontrolerFormeZaPrikazSlike implements Initializable {
    @FXML
    protected ImageView slika;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        PopisniKrug popisniKrug = KontrolerFormeZaDodjeluPopisnihKrugovaPopisivacu.popisniKrugovi.get(KontrolerFormeZaDodjeluPopisnihKrugovaPopisivacu.index);
        slika.setImage(new Image(new ByteArrayInputStream(popisniKrug.getSlikaBytes())));
    }
}
