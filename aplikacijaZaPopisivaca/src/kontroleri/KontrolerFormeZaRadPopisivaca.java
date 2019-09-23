package kontroleri;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class KontrolerFormeZaRadPopisivaca {
    static Stage popisStanovnikaStage;
    static Stage popisDomacinstvaStage;
    static Stage promjenaPismaStage;
    static Stage promjenaJezikaStage;
    static Stage pregledSacuvanihPopisnicaStage;
    static Stage pregledMapePopisnogKrugaStage;
    
    @FXML
    private Button odjaviSeButton;

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
    	try {
    		pregledSacuvanihPopisnicaStage = new Stage();
    		pregledSacuvanihPopisnicaStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaPrikazSacuvanihPopisnica.fxml"));
            pregledSacuvanihPopisnicaStage.setScene(new Scene(root));
            pregledSacuvanihPopisnicaStage.setResizable(false);
            pregledSacuvanihPopisnicaStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void pregledajMapuPopisnogKrugaButtonAction() {
    	try {
    		pregledMapePopisnogKrugaStage = new Stage();
    		pregledMapePopisnogKrugaStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaPrikazMapePopisnogKruga.fxml"));
            pregledMapePopisnogKrugaStage.setScene(new Scene(root));
            pregledMapePopisnogKrugaStage.setResizable(false);
            pregledMapePopisnogKrugaStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    private void promijeniJezikIPismoButtonAction() {
    	try {
            promjenaJezikaStage = new Stage();
            promjenaJezikaStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaOdabirJezikaIPisma.fxml"));
            promjenaJezikaStage.setScene(new Scene(root,450,230));
            promjenaJezikaStage.setResizable(false);
            promjenaJezikaStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    private void odjaviSe() {
    	Stage stage = (Stage)odjaviSeButton.getScene().getWindow();
	    stage.close();
    }
}
