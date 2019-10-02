package kontroleri;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import eCensus.rest.client.PopisivacCMISKlijent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;
import util.PrikazObavjestenja;

public class KontrolerFormeZaPrikazMapePopisnogKruga {
	@FXML
	private ListView<String> listaUlica;
	
	private List<PopisniKrug> popisniKrugovi;
	
	public static HashMap<PopisniKrug, Stage> util;
	
	public KontrolerFormeZaPrikazMapePopisnogKruga() {
		KontrolerFormeZaRadPopisivaca.pregledMapePopisnogKrugaStage.setOnShowing((event) -> inicijalizujListuUlica());
	}
	
	@FXML
	private void pogledajMapuButtonAction() {
		for(PopisniKrug popisniKrug : popisniKrugovi) {
			try {
				Stage stage = util.get(popisniKrug);
				Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "MapaPopisnogKruga.fxml"));
				stage.setScene(new Scene(root));
				stage.setResizable(false);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.show();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void inicijalizujListuUlica() {
		if(KontrolerFormeZaPrijavu.korisnik == null)
			PrikazObavjestenja.prikaziUpozorenje("Nema internet konekcije. Pokusajte se prijaviti ponovo.");
		else {
			PopisivacCMISKlijent klijent = new PopisivacCMISKlijent(KontrolerFormeZaPrijavu.korisnik);
			popisniKrugovi = (List<PopisniKrug>)klijent.getListaPopisnihKrugova((int)KontrolerFormeZaPrijavu.korisnik.getId()).getEntity();
			util = new HashMap<PopisniKrug, Stage>();
			for(PopisniKrug popisniKrug : popisniKrugovi) {
				util.put(popisniKrug, new Stage());
				List<String> ulice = popisniKrug.getUlice();
				for(String ulica : ulice)
					listaUlica.getItems().add(ulica);
			}
		}
	}
}
