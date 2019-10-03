package kontroleri;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import eCensus.rest.client.PopisivacCMISKlijent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
				Stage stage = new Stage();
				ImageView imageView = new ImageView();
				imageView.setFitHeight(500);
				imageView.setFitWidth(800);
				imageView.setImage(new Image(new ByteArrayInputStream(popisniKrug.getSlikaBytes())));
				AnchorPane pane = new AnchorPane(imageView);
				stage.setScene(new Scene(pane));
				stage.setResizable(false);
				stage.setTitle("Mapa popisnog kruga");
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
			popisniKrugovi = Arrays.asList(klijent.getListaPopisnihKrugova((int)KontrolerFormeZaPrijavu.korisnik.getId()).readEntity(PopisniKrug[].class));
			util = new HashMap<PopisniKrug, Stage>();
			for(PopisniKrug popisniKrug : popisniKrugovi) {
				util.put(popisniKrug, new Stage());
				List<String> ulice = popisniKrug.getUlice();
				for(String ulica : ulice) {
					listaUlica.getItems().add(ulica);
				}
			}
		}
	}
}
