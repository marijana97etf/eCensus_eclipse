package util;

import javafx.scene.control.Alert;
import main.Main;

public class PrikazObavjestenja {
	public static void prikaziInfo(String poruka){
    	String info = "Informacija";
    	if("српски".equals(Main.trenutniJezik)) {
    		poruka = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
    		info = PromjenaPisma.zamijeniLatinicuCiricom(info);
    	}

        Alert userNotSelectedAlert = new Alert(Alert.AlertType.INFORMATION);
	    userNotSelectedAlert.setTitle(info);
	    userNotSelectedAlert.setHeaderText(info + "!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }

	public static void prikaziUpozorenje(String poruka){
    	String greska = "Greška";
    	if("српски".equals(Main.trenutniJezik)) {
    		poruka = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
    		greska = PromjenaPisma.zamijeniLatinicuCiricom(greska);
    	}

        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
	    userNotSelectedAlert.setTitle(greska);
	    userNotSelectedAlert.setHeaderText(greska + "!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }
}
