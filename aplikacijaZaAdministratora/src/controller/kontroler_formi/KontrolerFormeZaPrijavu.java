package controller.kontroler_formi;

import controller.KontrolerZaJezikeIPisma.KontrolerZaJezik;
import controller.KontrolerZaJezikeIPisma.PromjenaPisma;
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
import model.pracenje_popisa.PISMO;
import test.Aplikacija;
import util.SecureLozinkaFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.ws.rs.core.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
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

	static KontrolerZaJezik kontrolerZaJezik = new KontrolerZaJezik();
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
		
		AdministratorCMISKlijent klijent = new AdministratorCMISKlijent(keystore, keystoreLozinka,
				trustStore, trustStoreLozinka, kosinickoImeInput, KorisnikSistema.napraviHesLozinke(password.getText()));
		Response odgovor = klijent.post(cmisResursUrl + "/login",kosinickoImeInput);

		if (Response.Status.UNAUTHORIZED.equals(odgovor.getStatusInfo())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			String nepostojeciNalog = "Korisničko ime ili lozinka nisu ispravni.";
			alert.setContentText(
					nepostojeciNalog + System.lineSeparator() + kontrolerZaJezik.latinToCyrillic(nepostojeciNalog));
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
			var uspjesnaPrijavaAdminAgencije="Uspješno ste se prijavili kao administrator agencije.";
			if(korisnikSistema.getPismo()!=null && korisnikSistema.getPismo().equals(PISMO.CIRILICA))
				uspjesnaPrijavaAdminAgencije = PromjenaPisma.zamijeniLatinicuCiricom(uspjesnaPrijavaAdminAgencije);
			alert.setContentText(uspjesnaPrijavaAdminAgencije + System.lineSeparator()
					+ kontrolerZaJezik.latinToCyrillic(uspjesnaPrijavaAdminAgencije));
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"));
			trenutniStage.setScene(new Scene(root));
		}

		else if (korisnikSistema instanceof ClanPKLS)
		{
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			String poruka = "Uspješno ste se prijavili kao član PKLS.";
			if(korisnikSistema.getPismo()!=null && korisnikSistema.getPismo().equals(PISMO.CIRILICA))
				poruka = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
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
			String poruka = "Uspješno ste se prijavili kao državno/entitetski instruktor.";
			if(korisnikSistema.getPismo()!=null && korisnikSistema.getPismo().equals(PISMO.CIRILICA))
				poruka = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
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
			String poruka = "Uspješno ste se prijavili kao OG instruktor.";
			if(korisnikSistema.getPismo()!=null && korisnikSistema.getPismo().equals(PISMO.CIRILICA))
				poruka = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
			alert.setContentText(poruka);
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadOGInstruktora.fxml"));
			trenutniStage.setScene(new Scene(root));
		}

		else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Tip naloga nije validan!");
			alert.showAndWait();
			return;
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {

		trenutniStage = Aplikacija.getStage();
	}
}
