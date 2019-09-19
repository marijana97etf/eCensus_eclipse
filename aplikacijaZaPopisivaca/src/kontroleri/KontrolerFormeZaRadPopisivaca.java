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
    static Stage popisDomacinstvaStage;
    static Stage promjenaPismaStage;

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
        try {
            Stage popisDomacinstvaStage = new Stage();
            KontrolerFormeZaRadPopisivaca.popisDomacinstvaStage = popisDomacinstvaStage;
            popisDomacinstvaStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaPopisivanjeDomacinstva.fxml"));
            popisDomacinstvaStage.setScene(new Scene(root,1115,600));
            popisDomacinstvaStage.setResizable(true);
            popisDomacinstvaStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
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
    	try {
            promjenaPismaStage = new Stage();
            promjenaPismaStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaOdabirPisma.fxml"));
            promjenaPismaStage.setScene(new Scene(root,450,230));
            promjenaPismaStage.setResizable(false);
            promjenaPismaStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
