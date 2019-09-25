package kontroleri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.core.Response;

import eCensus.rest.client.PowerUserCMISKlijent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Main;
import model.korisnicki_nalozi.KorisnikSistema;
import model.korisnicki_nalozi.Popisivac;
import util.PrikazObavjestenja;
import util.SecureLozinkaFactory;

public class KontrolerFormeZaPrijavu {
    private static final String CONFIG_FILE_PATH = "resources" + File.separator + "config.properties";
    public static KorisnikSistema korisnik;
    public static Stage promjenaLozinkeStage;
    
	@FXML
    private TextField UnosKorisnickogImenaField;
    @FXML
    private PasswordField UnosLozinkeField;

    public void prijavaNaSistemButtonAction() {
        String korisnickoIme = UnosKorisnickogImenaField.getText();
        String lozinka = UnosLozinkeField.getText();

        if(korisnickoIme.isEmpty() || lozinka.isEmpty()) {
        	if(!"српски".equals(Main.trenutniJezik))
        		PrikazObavjestenja.prikaziUpozorenje("Morate unijeti korisničko ime i lozinku.");
        	else
        		PrikazObavjestenja.prikaziUpozorenje("Морате унијети корисничко име и лозинку.");
        }
        else{
        	try {
	        	Properties properties = new Properties();
	        	properties.load(new FileInputStream(new File(CONFIG_FILE_PATH)));
	        	String keystore = properties.getProperty("DEFAULT_KEYSTORE");
	        	String truststore = properties.getProperty("DEFAULT_TRUSTSTORE");
	        	
	        	SecureLozinkaFactory factory = new SecureLozinkaFactory();
				String keystoreLozinka = factory.dekriptujLozinku(properties.getProperty("KEYSTORE_PASSWORD_CIPHER"));
				String trustStoreLozinka = factory.dekriptujLozinku(properties.getProperty("TRUSTSTORE_PASSWORD_CIPHER"));
				String cmisResursURL = properties.getProperty("CMIS_RESURS_URL");
				
				PowerUserCMISKlijent klijent = new PowerUserCMISKlijent(keystore, keystoreLozinka, truststore, trustStoreLozinka, korisnickoIme, KorisnikSistema.napraviHesLozinke(lozinka));
				Response odgovor = klijent.post(cmisResursURL + "/login", korisnickoIme);
				
				if (Response.Status.UNAUTHORIZED.equals(odgovor.getStatusInfo())) {
        			PrikazObavjestenja.prikaziUpozorenje("Pogrešno korisničko ime i lozinka.");
        		}       		
        		else if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        			odgovor = klijent.get(cmisResursURL + "/korisnici/nalozi/" + korisnickoIme);
        			if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
        				korisnik = odgovor.readEntity(Popisivac.class);
        				korisnik.setKeyStore(keystore);
        				korisnik.setKeyLozinka(keystoreLozinka);
        				korisnik.setTrustStore(truststore);
        				korisnik.setTrustLozinka(trustStoreLozinka);
        				
        				if("admin".equals(lozinka)) {
        					Main.primaryStage.hide();
	        				Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaPromjenuLozinke.fxml"));
	        				Scene scene = new Scene(root);
	        				promjenaLozinkeStage = new Stage();
	        				promjenaLozinkeStage.setScene(scene);
	        				promjenaLozinkeStage.initStyle(StageStyle.UNDECORATED);
	        				promjenaLozinkeStage.show();
        				}
        				else {
	        				Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRadPowerUsera.fxml"));
	        	            Main.primaryStage.setScene(new Scene(root));
        				}
        			} else {
        				PrikazObavjestenja.prikaziUpozorenje("Greška na serveru.");
        			}
        		} else {
        			PrikazObavjestenja.prikaziUpozorenje("Greška na serveru.");
        		}
        	}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			e.printStackTrace();
        }
        }
    }
}
