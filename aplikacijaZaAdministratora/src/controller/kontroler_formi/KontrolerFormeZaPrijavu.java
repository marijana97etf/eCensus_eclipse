package controller.kontroler_formi;

import eCensus.rest.client.AdministratorCMISKlijent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jdk.jshell.spi.ExecutionControl;
import model.korisnicki_nalozi.*;
import test.Aplikacija;
import util.SecureLozinkaFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.Response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.ConnectException;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;

public class KontrolerFormeZaPrijavu implements Initializable {


	private Stage trenutniStage;
	private static KorisnikSistema trenutniKorisnik;

	public static KorisnikSistema getTrenutniKorisnik() {
		return trenutniKorisnik;
	}

	@FXML
	TextField username;
	@FXML
	PasswordField password;

	public void login(ActionEvent actionEvent) throws IOException, ExecutionControl.NotImplementedException {

		String kosinickoImeInput = username.getText();

		KorisnikSistema korisnikSistema = null;

		String cmisResursUrl = null,keystore = null,trustStore=null;
		String keystoreLozinka=null,trustStoreLozinka=null;

		try(Reader configReader = new FileReader(Aplikacija.CONFIG_FILE)){
			Properties properties = new Properties();
			properties.load(configReader);
			cmisResursUrl = properties.getProperty("CMIS_RESURS_URL");
			keystore = properties.getProperty("DEFAULT_KEYSTORE");
			trustStore = properties.getProperty("DEFAULT_TRUSTSTORE");
			
			SecureLozinkaFactory factory = new SecureLozinkaFactory();
			keystoreLozinka = factory.dekriptujLozinku(properties.getProperty("KEYSTORE_PASSWORD_CIPHER"));
			trustStoreLozinka = factory.dekriptujLozinku(properties.getProperty("TRUSTSTORE_PASSWORD_CIPHER"));
			
		} catch (FileNotFoundException e) {
			Aplikacija.connLogger.getLogger().log(Level.SEVERE,e.getMessage(),e);
		} catch (IOException e) {
			Aplikacija.connLogger.getLogger().log(Level.SEVERE,e.getMessage(),e);
		} catch (NoSuchAlgorithmException e) {
			Aplikacija.connLogger.getLogger().log(Level.SEVERE,e.getMessage(),e);
		} catch (InvalidKeyException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
			Aplikacija.connLogger.getLogger().log(Level.SEVERE,e.getMessage(),e);
		}

		AdministratorCMISKlijent klijent = null;
		Response odgovor = null;
		try {
			klijent = new AdministratorCMISKlijent(keystore, keystoreLozinka,
					trustStore, trustStoreLozinka, kosinickoImeInput, KorisnikSistema.napraviHesLozinke(password.getText()));
			odgovor = klijent.post(cmisResursUrl + "/login", kosinickoImeInput);
		}
		catch (ProcessingException ce)
		{
			Alert alert = new Alert(Alert.AlertType.ERROR);
			String nepostojeciNalog = Aplikacija.prevediRecenicu("Konekcija sa serverom nije u redu.");
			alert.setContentText(nepostojeciNalog);
			alert.showAndWait();
			ce.getMessage();
			return;
		}

		if (Response.Status.UNAUTHORIZED.equals(odgovor.getStatusInfo())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			String nepostojeciNalog = Aplikacija.prevediRecenicu("Korisničko ime ili lozinka nisu ispravni.");
			alert.setContentText(nepostojeciNalog);
			alert.showAndWait();
			return;
		}
		if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
			String tipKorisnika = odgovor.readEntity(String.class);
			odgovor = klijent.get(cmisResursUrl + "/korisnici/nalozi/" + kosinickoImeInput);
			if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
				if(tipKorisnika.equals(AdministratorAgencije.class.getName()))
					korisnikSistema = odgovor.readEntity(AdministratorAgencije.class);
				else if(tipKorisnika.equals(ClanPKLS.class.getName()))
					korisnikSistema = odgovor.readEntity(ClanPKLS.class);
				else if(tipKorisnika.equals(DEInstruktor.class.getName()))
					korisnikSistema = odgovor.readEntity(DEInstruktor.class);
				else if(tipKorisnika.equals(OGInstruktor.class.getName()))
					korisnikSistema = odgovor.readEntity(OGInstruktor.class);
				else if(tipKorisnika.equals(Popisivac.class.getName())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(Aplikacija.prevediRecenicu("Popisivač nema mogućnost prijave u ovoj aplikaciji."));
					alert.showAndWait();
					Aplikacija.connLogger.getLogger().log(Level.WARNING, "Pokušaj prijavljivanja popisivača u aplikaciji za admina.");
					return;
				}
				else if(tipKorisnika.equals(PowerUser.class.getName())) {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText(Aplikacija.prevediRecenicu("Korisik nema mogućnost prijave u ovoj aplikaciji."));
					alert.showAndWait();
					Aplikacija.connLogger.getLogger().log(Level.WARNING, "Pokušaj prijavljivanja power korisnika u aplikaciji za admina.");
					return;
				}
			} else {
				Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
			}
		} else {
			Aplikacija.connLogger.logHeaders(Level.SEVERE, odgovor);
		}

		trenutniKorisnik = korisnikSistema;
		trenutniKorisnik.setKeyStore(keystore);
		trenutniKorisnik.setKeyLozinka(keystoreLozinka);
		trenutniKorisnik.setTrustStore(trustStore);
		trenutniKorisnik.setTrustLozinka(trustStoreLozinka);

		if (korisnikSistema instanceof AdministratorAgencije)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			var uspjesnaPrijavaAdminAgencije=Aplikacija.prevediRecenicu("Uspješno ste se prijavili kao administrator agencije.");
			alert.setContentText(uspjesnaPrijavaAdminAgencije);
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratoraAgencije.fxml"));
			trenutniStage.setScene(new Scene(root));
		}

		else if (korisnikSistema instanceof ClanPKLS)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			String poruka = Aplikacija.prevediRecenicu("Uspješno ste se prijavili kao član PKLS.");
			alert.setContentText(poruka);
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"));
			trenutniStage.setScene(new Scene(root));
		}

		else if (korisnikSistema instanceof DEInstruktor)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			String poruka = Aplikacija.prevediRecenicu("Uspješno ste se prijavili kao državno/entitetski instruktor.");
			alert.setContentText(poruka);
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadDEInstruktora.fxml"));
			trenutniStage.setScene(new Scene(root));
		}

		else if (korisnikSistema instanceof OGInstruktor)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			String poruka = Aplikacija.prevediRecenicu("Uspješno ste se prijavili kao OG instruktor.");
			alert.setContentText(poruka);
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadOGInstruktora.fxml"));
			trenutniStage.setScene(new Scene(root));
		}

		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText(Aplikacija.prevediRecenicu("Tip naloga nije validan!"));
			alert.showAndWait();
			return;
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		trenutniStage = Aplikacija.getStage();
	}
}
