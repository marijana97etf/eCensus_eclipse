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
import util.PromjenaPisma;

public class KontrolerZaPromjenuJezikaIPisma {
	private List<Triplet<String, String, String>> rjecnik;
	
	@FXML
	private ComboBox<String> jezikIPismoComboBox;
	@FXML
	private Label trenutniJezikIPismoLabel;
	
	public KontrolerZaPromjenuJezikaIPisma() {
		KontrolerFormeZaRadPopisivaca.promjenaJezikaStage.setOnShowing((event) -> {
			trenutniJezikIPismoLabel.setText(getProperty());
			dodajJezikeIPisma();
			procitajRjecnik();
		});
	}
	
	private void dodajJezikeIPisma() {
		List<String> jeziciIPisma = new ArrayList<>();
		if("српски".equals(Main.trenutniJezik)) {
			jeziciIPisma.add("српски, ћирилица");
			jeziciIPisma.add("бошњачки, латиница");
			jeziciIPisma.add("хрватски, латиница");
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
			prikaziUpozorenje("Morate odabrati jezik i pismo različito od trenutnog");
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
										
				String izmjena = pronadjiIZamijeni(original, odabraniJezik);
						
				prepisiFajl(file, izmjena);	            
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String pronadjiIZamijeni(String linija, String odabraniJezik) {
		String novaLinija = linija;

		for(Triplet<String, String, String> t : rjecnik) {
			String pattern;
			if("српски".equals(Main.trenutniJezik))
				pattern = t.getValue0();
			else if("bošnjački".equals(Main.trenutniJezik))
				pattern = t.getValue1();
			else
				pattern = t.getValue2();
			
			Pattern myPattern;
			if(!"POL".equals(pattern))
				myPattern = Pattern.compile(pattern);
			else
				myPattern = Pattern.compile(pattern + ":");
			Matcher matcher = myPattern.matcher(linija);
			List<String> pronadjeno = new ArrayList<>();
			
			while (matcher.find()) {
			    pronadjeno.add(matcher.group());
			}
	
			for(String rijec : pronadjeno) {
				String novaRijec;
				if("srpski".equals(odabraniJezik))
					novaRijec = t.getValue0();
				else if("bošnjački".equals(odabraniJezik) || "бошњачки".equals(odabraniJezik))
					novaRijec = t.getValue1();
				else
					novaRijec = t.getValue2();
				if("POL".equals(pattern))
					novaLinija = novaLinija.replaceAll(rijec, novaRijec + ":");
				else
					novaLinija = novaLinija.replaceAll(rijec, novaRijec);
			}
		}
		return novaLinija;
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
	
	private void procitajRjecnik() {
		List<String> lines = new ArrayList<>();
		rjecnik = new ArrayList<>();
        try {
            Path path = Paths.get("resources" + File.separator + "rjecnikJezici.txt");
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line : lines){
        	rjecnik.add(new Triplet<String, String, String>(line.split("-")[0], line.split("-")[1], line.split("-")[2]));
        }
	}
	
	private void prepisiFajl(File file, String sadrzaj) throws IOException {
		BufferedWriter writer =
	             new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src" + File.separator + "forme" + File.separator + file.getName()), StandardCharsets.UTF_8));
		writer.write(sadrzaj);
	    writer.close();
	}
	
	private void prikaziUpozorenje(String poruka){
    	String greska = "Greška";
    	if("српски".equals(Main.trenutniJezik)) {
    		poruka = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
    		greska = PromjenaPisma.zamijeniLatinicuCiricom(poruka);
    	}
    	
        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
	    userNotSelectedAlert.setTitle(greska);
	    userNotSelectedAlert.setHeaderText(greska + "!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }
}