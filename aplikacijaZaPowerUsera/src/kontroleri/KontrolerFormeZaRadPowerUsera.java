package kontroleri;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KontrolerFormeZaRadPowerUsera {
	public static Stage promjenaJezikaStage;
	public static Stage registracijaAdministratoraAgencijeStage;
	
	@FXML
    private Button logoutButton;
	
	@FXML
	private void registrujAdministratoraAgencije() {
		try {
			registracijaAdministratoraAgencijeStage = new Stage();
			registracijaAdministratoraAgencijeStage.initModality(Modality.APPLICATION_MODAL);

            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRegistracijuAdministratoraAgencije.fxml"));
            registracijaAdministratoraAgencijeStage.setScene(new Scene(root));
            registracijaAdministratoraAgencijeStage.setResizable(false);
            registracijaAdministratoraAgencijeStage.show();
        }
        catch(IOException e){
            e.printStackTrace();
        }
	}
	
	@FXML
	private void promijeniJezikIPismo() {
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
	private void logout() {
		Stage stage = (Stage)logoutButton.getScene().getWindow();
	    stage.close();
	}
}
