package kontroleri;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import javax.ws.rs.core.Response;

import eCensus.rest.client.PopisivacCMISKlijent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.Main;
import model.korisnicki_nalozi.AdministratorAgencije;
import model.korisnicki_nalozi.ClanPKLS;
import model.korisnicki_nalozi.DEInstruktor;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.OGInstruktor;
import model.korisnicki_nalozi.Popisivac;

public class KontrolerFormeZaPrijavu {
    private static final String CONFIG_FILE_PATH = "resources" + File.separator + "config.properties";
    public static KorisnikSistema korisnik;

    @FXML
    private TextField UnosKorisnickogImenaField;
    @FXML
    private PasswordField UnosLozinkeField;

    public void prijavaNaSistemButtonAction() {
        String korisnickoIme = UnosKorisnickogImenaField.getText();
        String lozinka = UnosLozinkeField.getText();

        if(korisnickoIme.isEmpty() || lozinka.isEmpty()) {
        	if(!"српски".equals(Main.trenutniJezik))
        		prikaziUpozorenje("Morate unijeti korisničko ime i lozinku.");
        	else
        		prikaziUpozorenje("Морате унијети корисничко име и лозинку.");
        }
        else{
            try {
                String hashLozinke = KorisnikSistema.napraviHesLozinke(lozinka);

                Properties properties = new Properties();
            	properties.load(new FileInputStream(new File(CONFIG_FILE_PATH)));
            	String keystore = properties.getProperty("DEFAULT_KEYSTORE");
            	String truststore = properties.getProperty("DEFAULT_TRUSTSTORE");
            	String cmisResursURL = properties.getProperty("CMIS_RESURS_URL");
            	
//                PopisivacCMISKlijent klijent = new PopisivacCMISKlijent(keystore, "sigurnost",
//        				truststore, "sigurnost", korisnickoIme, KorisnikSistema.napraviHesLozinke(hashLozinke));
//        		Response odgovor = klijent.post(cmisResursURL + "/login", korisnickoIme);
//
//        		if (Response.Status.UNAUTHORIZED.equals(odgovor.getStatusInfo())) {
//        			prikaziUpozorenje("Pogrešno korisničko ime i lozinka.");
//        		}       		
//        		else if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
//        			odgovor = klijent.get(cmisResursURL + "/korisnici/nalozi/" + korisnickoIme);
//        			if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
//        				korisnik = odgovor.readEntity(Popisivac.class);
        				Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRadPopisivaca.fxml"));
                        Main.primaryStage.setScene(new Scene(root));
//        			} else {
//        				prikaziUpozorenje("Greška na serveru.");
//        			}
//        		} else {
//        			prikaziUpozorenje("Greška na serveru.");
//        		}
                            
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    private void prikaziUpozorenje(String poruka){
    	String greska = "Greška";
    	if("српски".equals(Main.trenutniJezik))
    		greska = "Грешка";
    	
        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
        userNotSelectedAlert.setTitle("greska");
        userNotSelectedAlert.setHeaderText(greska + "!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }
}
