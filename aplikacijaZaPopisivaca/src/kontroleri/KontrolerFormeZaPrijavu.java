package kontroleri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ConnectException;
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
import util.PrikazObavjestenja;
import util.SecureLozinkaFactory;

public class KontrolerFormeZaPrijavu {
    private static final String CONFIG_FILE_PATH = "resources" + File.separator + "config.properties";
    public static KorisnikSistema korisnik;
    
    String keystore;
    String truststore;
    String keystoreLozinka;
    String trustStoreLozinka;

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
            	keystore = properties.getProperty("DEFAULT_KEYSTORE");
            	truststore = properties.getProperty("DEFAULT_TRUSTSTORE");
            	String cmisResursURL = properties.getProperty("CMIS_RESURS_URL");
            	
    			SecureLozinkaFactory factory = new SecureLozinkaFactory();
    			keystoreLozinka = factory.dekriptujLozinku(properties.getProperty("KEYSTORE_PASSWORD_CIPHER"));
    			trustStoreLozinka = factory.dekriptujLozinku(properties.getProperty("TRUSTSTORE_PASSWORD_CIPHER"));
            	
                PopisivacCMISKlijent klijent = new PopisivacCMISKlijent(keystore, keystoreLozinka,
        				truststore, trustStoreLozinka, korisnickoIme, KorisnikSistema.napraviHesLozinke(lozinka));
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

        				Properties p = new Properties();
        				FileInputStream in = new FileInputStream(new File("." + File.separator + "resources" + File.separator + "kredencijali.properties"));
        				p.load(in);
        				in.close();
        				
        				FileOutputStream out = new FileOutputStream("." + File.separator + "resources" + File.separator + "kredencijali.properties");
        				p.setProperty("USERNAME", korisnickoIme);
        				p.setProperty("PASSWORD", lozinka);
        				p.store(out, null);
        				out.close();
        				
        				Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRadPopisivaca.fxml"));
                        Main.primaryStage.setScene(new Scene(root));
        			} else {
        				PrikazObavjestenja.prikaziUpozorenje("Greška na serveru.");
        			}
        		} else {
        			PrikazObavjestenja.prikaziUpozorenje("Greška na serveru.");
        		}
                            
            }
            catch(ConnectException e) {
            	try {
            		Properties properties = new Properties();
            		properties.load(new FileInputStream(new File("resources" + File.separator + "kredencijali.properties")));
            		String username = properties.getProperty("USERNAME");
            		String password = properties.getProperty("PASSWORD");
            		
            		if(username.isEmpty() || password.isEmpty())
            			PrikazObavjestenja.prikaziUpozorenje("Nije moguca prva prijava bez pristupa internetu.");
            		else if(korisnickoIme.equals(username) && (KorisnikSistema.napraviHesLozinke(lozinka)).equals(password)){          			
            			Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "FormaZaRadPopisivaca.fxml"));
                        Main.primaryStage.setScene(new Scene(root));
            		}
            		else
            			PrikazObavjestenja.prikaziUpozorenje("Pogresno korisnicko ime i lozinka.");
            	}
            	catch(Exception ex) {
            		ex.printStackTrace();
            	}
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
