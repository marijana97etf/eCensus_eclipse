package controller.kontroler_formi;

import controller.KontrolerZaJezikeIPisma.KontrolerZaJezik;
import eCensus.rest.client.AdministratorAgencijeCMISKlijent;
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
import test.Pokreni_GUI_Aplikaciju;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.ws.rs.core.Response;

public class KontrolerFormeZaPrijavu implements Initializable {

	private Stage trenutniStage;
	private static KorisnikSistema trenutniKorisnik;

	private SkladisteNaloga nalozi;

	public static KorisnikSistema getTrenutniKorisnik() {
		return trenutniKorisnik;
	}

	protected static final String CMIS_RESURS_URL = "https://localhost:8443/CMISServer/rest/CMIS";
	public static String TRUSTSTORE = "resources" + File.separator + "clientTrustStore.p12";
	public static String KEYSTORE = "resources" + File.separator + "clientStore.p12";

	static KontrolerZaJezik kontrolerZaJezik = new KontrolerZaJezik();
	@FXML
	TextField username;
	@FXML
	PasswordField password;

	public void login(ActionEvent actionEvent) throws IOException, ExecutionControl.NotImplementedException {

		String kosinickoImeInput = username.getText();

		// KorisnikSistema korisnikSistema = nalozi.stream().filter(e->
		// e.getKorisnickoIme().equals(usernameInput)).findFirst().get();
		KorisnikSistema korisnikSistema = null;

		AdministratorAgencijeCMISKlijent klijent = new AdministratorAgencijeCMISKlijent(KEYSTORE, "sigurnost",
				TRUSTSTORE, "sigurnost", kosinickoImeInput, KorisnikSistema.napraviHesLozinke(password.getText()));
		Response odgovor = klijent.get(CMIS_RESURS_URL);

		if (Response.Status.UNAUTHORIZED.equals(odgovor.getStatusInfo())) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			String nepostojeciNalog = "Korisničko ime ili lozinka nisu ispravni.";
			alert.setContentText(
					nepostojeciNalog + System.lineSeparator() + kontrolerZaJezik.latinToCyrillic(nepostojeciNalog));
			alert.showAndWait();
			return;
		}
		if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
			odgovor = klijent.get(CMIS_RESURS_URL + "/korisnici/nalozi/" + kosinickoImeInput);
			if (Response.Status.Family.SUCCESSFUL.equals(odgovor.getStatusInfo().getFamily())) {
				korisnikSistema = odgovor.readEntity(AdministratorAgencije.class);
			} else {
				// logguju se header-i
			}
		} else {
			// logguju se header-i
		}

		trenutniKorisnik = korisnikSistema;
		if (korisnikSistema instanceof AdministratorAgencije) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			var uspjesnaPrijavaAdminAgencije = "Uspješno ste se prijavili kao administrator agencije.";
			alert.setContentText(uspjesnaPrijavaAdminAgencije + System.lineSeparator()
					+ kontrolerZaJezik.latinToCyrillic(uspjesnaPrijavaAdminAgencije));
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadAdministratora.fxml"));
			trenutniStage.setScene(new Scene(root));
		} else if (korisnikSistema instanceof ClanPKLS) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Uspješno ste se prijavili kao clan PKLS.");
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadClanaPKLS.fxml"));
			trenutniStage.setScene(new Scene(root));
		} else if (korisnikSistema instanceof DEInstruktor) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setContentText("Uspješno ste se prijavili kao DE instruktor.");
			ButtonType buttonType = alert.showAndWait().get();
			if (!buttonType.getText().equals("OK"))
				return;
			Parent root = FXMLLoader.load(getClass().getResource("/view/FormaZaRadDEInstruktora.fxml"));
			trenutniStage.setScene(new Scene(root));
		} else if (korisnikSistema instanceof OGInstruktor) {
			throw new ExecutionControl.NotImplementedException("OGInstruktor - not implemented");
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setContentText("Tip naloga nije validan!");
			alert.showAndWait();
			return;
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		
		trenutniStage = Pokreni_GUI_Aplikaciju.getStage();
		
	}
}
