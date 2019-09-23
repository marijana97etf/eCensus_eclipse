 package kontroleri;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javatuples.Triplet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;
import util.PrikazObavjestenja;
import util.PromjenaJezika;
import util.PromjenaPisma;

public class KontrolerZaPromjenuJezikaIPisma {

	@FXML
	private ComboBox<String> jezikIPismoComboBox;
	@FXML
	private Label trenutniJezikIPismoLabel;

	public KontrolerZaPromjenuJezikaIPisma() {
		KontrolerFormeZaRadPowerUsera.promjenaJezikaStage.setOnShowing((event) -> {
			trenutniJezikIPismoLabel.setText(getProperty());
			dodajJezikeIPisma();
		});
	}

	private void dodajJezikeIPisma() {
		List<String> jeziciIPisma = new ArrayList<>();
		if("српски".equals(Main.trenutniJezik)) {
			jeziciIPisma.add("српски, ћирилица");
			jeziciIPisma.add("бошњачки, латиница");
			jeziciIPisma.add("хрватски‚ латиница");
		}
		else {
			jeziciIPisma.add("srpski, ćirilica");
			jeziciIPisma.add("bošnjački, latinica");
			jeziciIPisma.add("hrvatski, latinica");
		}
		ObservableList<String> list = FXCollections.observableList(jeziciIPisma);
		jezikIPismoComboBox.setItems(list);
	}

	@FXML
	private void promijeniJezikIPismo() {
		String odabraniJezik = jezikIPismoComboBox.getSelectionModel().getSelectedItem().split(",")[0];
		if(odabraniJezik != null && !Main.trenutniJezik.equals(odabraniJezik)) {
			if("српски".equals(Main.trenutniJezik))
				PromjenaPisma.promijeniPismo("latinica");
			prevedi(odabraniJezik);
			if("srpski".equals(odabraniJezik))
				PromjenaPisma.promijeniPismo("ćirilica");
			try {
				setProperty(jezikIPismoComboBox.getSelectionModel().getSelectedItem());

				Stage stage = new Stage();
	            stage.initModality(Modality.APPLICATION_MODAL);

	            Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "UspjesnaPromjenaJezikaIPisma.fxml"));
	            stage.setScene(new Scene(root));
	            stage.setResizable(false);
	            stage.showAndWait();

	            System.exit(0);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else
			PrikazObavjestenja.prikaziUpozorenje("Morate odabrati jezik i pismo različito od trenutnog");
	}

	private void prevedi(String odabraniJezik) {
		String formeDirektorijum = "src" + File.separator + "forme";
		File folder = new File(formeDirektorijum);
		File[] listOfFiles = folder.listFiles();

		for(File file : listOfFiles) {
			try {
				byte[] bytes = new byte[(int)file.length()];
				DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(formeDirektorijum + File.separator + file.getName())));
				dataInputStream.readFully(bytes);
				dataInputStream.close();
				String original = new String(bytes, StandardCharsets.UTF_8);

				String izmjena = PromjenaJezika.pronadjiIZamijeni(original, odabraniJezik);

				prepisiFajl(file, izmjena);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}

		if("srpski".equals(odabraniJezik))
			PromjenaPisma.promijeniPismo("ćirilica");
	}

	private String getProperty() {
		String jezik = "";
		try {
			Properties properties = new Properties();
			FileInputStream in = new FileInputStream(new File("." + File.separator + "resources" + File.separator + "config.properties"));
			properties.load(in);
			jezik = properties.getProperty("TRENUTNI_JEZIK_I_PISMO");
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return jezik;
	}

	private void setProperty(String property) throws IOException{
		String jezikIPismo = property;
		if("српски".equals(Main.trenutniJezik))
			jezikIPismo = PromjenaPisma.zamijeniCirilicuLatinicom(property);
		else if("srpski".equals(property.split(",")[0]))
			jezikIPismo = PromjenaPisma.zamijeniLatinicuCiricom(property);

		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(new File("." + File.separator + "resources" + File.separator + "config.properties"));
		properties.load(in);
		in.close();

		FileOutputStream out = new FileOutputStream("." + File.separator + "resources" + File.separator + "config.properties");
		properties.setProperty("TRENUTNI_JEZIK_I_PISMO", jezikIPismo);
		properties.store(out, null);
		out.close();
	}

	private void prepisiFajl(File file, String sadrzaj) throws IOException {
		BufferedWriter writer =
	             new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src" + File.separator + "forme" + File.separator + file.getName()), StandardCharsets.UTF_8));
		writer.write(sadrzaj);
	    writer.close();
	}
}
