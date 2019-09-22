package controller.kontroler_formi;

import controller.KontrolerZaJezikeIPisma.KontrolerZaJezik;
import eCensus.rest.client.AdministratorAgencijeCMISKlijent;
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
import test.Pokreni_GUI_Aplikaciju;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Map.Entry;

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

		AdministratorCMISKlijent klijent = new AdministratorCMISKlijent(KEYSTORE, "sigurnost",
				TRUSTSTORE, "sigurnost", kosinickoImeInput, KorisnikSistema.napraviHesLozinke(password.getText()));
		Response odgovor = klijent.post(CMIS_RESURS_URL + "/login",kosinickoImeInput);

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
			System.out.println(tipKorisnika);//za brisanje
			odgovor = klijent.get(CMIS_RESURS_URL + "/korisnici/nalozi/" + kosinickoImeInput);
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
				//za logg
            	System.out.println(odgovor.getStatusInfo().getStatusCode() + " " + odgovor.getStatusInfo().getReasonPhrase() );
            	for(Entry<String,List<Object>> entry : odgovor.getHeaders().entrySet()) {
            		System.out.print(entry.getKey() + " ");
            		for(Object objekat : entry.getValue())
            			System.out.print(objekat +" ");
            		System.out.println();
            	}
			}
		} else {
			//za logg
        	System.out.println(odgovor.getStatusInfo().getStatusCode() + " " + odgovor.getStatusInfo().getReasonPhrase() );
        	for(Entry<String,List<Object>> entry : odgovor.getHeaders().entrySet()) {
        		System.out.print(entry.getKey() + " ");
        		for(Object objekat : entry.getValue())
        			System.out.print(objekat +" ");
        		System.out.println();
        	}
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
