package kontroleri;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class KontrolerFormeZaRadPopisivaca {
    static Stage popisStanovnikaStage;

    @FXML
    private void popisiStanovnikaButtonAction() {
        try {
            Stage popisStanovnikaStage = new Stage();
            KontrolerFormeZaRadPopisivaca.popisStanovnikaStage = popisStanovnikaStage;
            popisStanovnikaStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaPopisivanjeStanovnika.fxml"));
            popisStanovnikaStage.setScene(new Scene(root,1115,600));
            popisStanovnikaStage.setResizable(true);
            popisStanovnikaStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void popisiDomacinstvoButtonAction() {
    }

    @FXML
    private void pregledajSacuvanePopisniceButtonAction() {
    }

    @FXML
    private void pregledajMapuPopisnogKrugaButtonAction() {
    }

    @FXML
    private void promijeniJezikButonAction() {
    }

    @FXML
    private void promijeniPismoButtonAction() {
    }
}
