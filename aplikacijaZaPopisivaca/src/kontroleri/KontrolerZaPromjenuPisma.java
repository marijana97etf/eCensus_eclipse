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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.Main;

public class KontrolerZaPromjenuPisma {
	static Stage uspjesnaPromjenaPismaStage;
	
	private Map<String, String> rjecnikLatinicaUCirilicu = new HashMap<>();
	private Map<String, String> rjecnikCirilicaULatinicu = new HashMap<>();
	
	@FXML
	private ComboBox<String> pismoComboBox;
	@FXML
	private Label trenutnoPismoLabel;
	
	public KontrolerZaPromjenuPisma() {
		KontrolerFormeZaRadPopisivaca.promjenaPismaStage.setOnShowing((event) -> {
			trenutnoPismoLabel.setText(getProperty());
			dodajPisma();
			procitajRjecnik("rjecnikLatinicaUCirilicu.txt", rjecnikLatinicaUCirilicu);
			procitajRjecnik("rjecnikCirilicaULatinicu.txt", rjecnikCirilicaULatinicu);
		});
	}
	
	private void dodajPisma() {
		List<String> pisma = new ArrayList<>();
		if("latinica".equals(Main.trenutnoPismo)) {
			pisma.add("latinica");
			pisma.add("ćirilica");
		}
		else {
			pisma.add("латиница");
			pisma.add("ћирилица");
		}
		ObservableList<String> list = FXCollections.observableList(pisma);
		pismoComboBox.setItems(list);
	}
	
	@FXML
	private void promijeniPismo() {
		String pismo = pismoComboBox.getSelectionModel().getSelectedItem();
		if(pismo != null && !Main.trenutnoPismo.equals(pismo)) {	
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
											
					String lines[] = original.split("\\r?\\n");
					
					List<String> linesAsList = Arrays.asList(lines);
					List<String> noveLinije = new ArrayList<>();
					
					for(String l : linesAsList) {
						String izmjena = pronadjiIZamijeni(l);
						noveLinije.add(izmjena);
					}
							
					prepisiFajl(file, noveLinije);	            
				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}
			try {
				setProperty(pismo);
								
				Parent root = FXMLLoader.load(getClass().getResource("/forme" + File.separator + "UspjesnaPromjenaPisma.fxml"));
		        Stage stage = new Stage();
		        uspjesnaPromjenaPismaStage = stage;
		        stage.setTitle("eCensus");
		        stage.setScene(new Scene(root));
		        stage.setResizable(false);
		        stage.showAndWait();
		        
				System.exit(0);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		else {
			if("latinica".equals(Main.trenutnoPismo) || "латиница".equals(Main.trenutnoPismo))
				prikaziUpozorenje("Morate odabrati pismo različito od trenutnog pisma");
			else
				prikaziUpozorenje("Морате одабрати писмо различито од тренутног писма");
		}
	}
	
	private String pronadjiIZamijeni(String linija) {
		String pattern = "ext=\"[^\"]*\"";
		Pattern myPattern = Pattern.compile(pattern);
		Matcher matcher = myPattern.matcher(linija);
		List<String> pronadjeno = new ArrayList<>();
		
		while (matcher.find()) {
		    pronadjeno.add(matcher.group());
		}

		String novaLinija = linija;
		for(String recenica : pronadjeno) {
			if(recenica.endsWith("?\"")) {
				String util = recenica.replace("?\"", "");
				String novaRecenica;
				if("latinica".equals(Main.trenutnoPismo))
					novaRecenica = zamijeniLatinicuCiricom(util.split("=")[1].replace("\"", ""));
				else
					novaRecenica = zamijeniCirilicuLatinicom(util.split("=")[1].replace("\"", ""));
				novaLinija = novaLinija.replaceAll(util, "ext=\"" + novaRecenica);
			}
			else {
				String novaRecenica;
				if("latinica".equals(Main.trenutnoPismo))
					novaRecenica = zamijeniLatinicuCiricom(recenica.split("=")[1].replace("\"", ""));
				else
					novaRecenica = zamijeniCirilicuLatinicom(recenica.split("=")[1].replace("\"", ""));
				novaLinija = novaLinija.replaceAll(recenica, "ext=\"" + novaRecenica + "\"");
			}
		}
		return novaLinija;
	}
	
	private String zamijeniLatinicuCiricom(String recenica) {
		recenica = recenica.replaceAll("LJ", rjecnikLatinicaUCirilicu.get("LJ"));
		recenica = recenica.replaceAll("Lj", rjecnikLatinicaUCirilicu.get("Lj"));
		recenica = recenica.replaceAll("lj", rjecnikLatinicaUCirilicu.get("lj"));
		recenica = recenica.replaceAll("NJ", rjecnikLatinicaUCirilicu.get("NJ"));
		recenica = recenica.replaceAll("Nj", rjecnikLatinicaUCirilicu.get("Nj"));
		recenica = recenica.replaceAll("nj", rjecnikLatinicaUCirilicu.get("nj"));
		recenica = recenica.replaceAll("Dž", rjecnikLatinicaUCirilicu.get("Dž"));
		recenica = recenica.replaceAll("dž", rjecnikLatinicaUCirilicu.get("dž"));
		
		String tmp = new String(recenica.toCharArray());
        for(var entry : rjecnikLatinicaUCirilicu.entrySet())
        {
            tmp = tmp.replaceAll(entry.getKey(), entry.getValue());
        }
        return tmp;
	}
	
	private String zamijeniCirilicuLatinicom(String recenica) {
		recenica = recenica.replaceAll("Љ", "Lj");
		recenica = recenica.replaceAll("љ", "lj");
		recenica = recenica.replaceAll("Њ", "Nj");
		recenica = recenica.replaceAll("њ", "nj");
		recenica = recenica.replaceAll("Џ", "Dž");
		recenica = recenica.replaceAll("џ", "dž");
		
		String tmp = new String(recenica.toCharArray());
        for(var entry : rjecnikCirilicaULatinicu.entrySet())
        {
            tmp = tmp.replaceAll(entry.getKey(), entry.getValue());
        }
        return tmp;
	}
	
	private void prepisiFajl(File file, List<String> linije) throws IOException {
		BufferedWriter writer =
	             new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src" + File.separator + "forme" + File.separator + file.getName()), StandardCharsets.UTF_8));
		for(String l : linije) {
			writer.write(l);
			writer.newLine();
		}
	    writer.close();
	}
	
	private void setProperty(String property) throws IOException{
		String pismo;
		if("latinica".equals(Main.trenutnoPismo))
			pismo = zamijeniLatinicuCiricom(property);
		else
			pismo = zamijeniCirilicuLatinicom(property);
		
		Properties properties = new Properties();
		FileInputStream in = new FileInputStream(new File("." + File.separator + "resources" + File.separator + "config.properties"));
		properties.load(in);
		in.close();
		
		FileOutputStream out = new FileOutputStream("." + File.separator + "resources" + File.separator + "config.properties");
		properties.setProperty("TRENUTNO_PISMO", pismo);
		properties.store(out, null);
		out.close();
	}
	
	private String getProperty() {
		String pismo = "";
		try {
			Properties properties = new Properties();
			FileInputStream in = new FileInputStream(new File("." + File.separator + "resources" + File.separator + "config.properties"));
			properties.load(in);
			pismo = properties.getProperty("TRENUTNO_PISMO");
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return pismo;
	}
	
	private void procitajRjecnik(String nazivRjecnika, Map<String, String> rjecnik) {
        List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get("resources" + File.separator + nazivRjecnika);
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line : lines)
        {
            String[] letters = line.split(" <=> ");
            rjecnik.put(letters[0], letters[1]);
        }
        
        if("rjecnikLatinicaUCirilicu".equals(nazivRjecnika))
        	rjecnik.put("Š", "Ш"); //iz nekog razloga samo veliko Š nece iz fajla
        else
        	rjecnik.put("Ш", "Š");
    }
	
	private void prikaziUpozorenje(String poruka){
		String s = "";
		if("latinica".equals(Main.trenutnoPismo) || "латиница".equals(Main.trenutnoPismo))
			s = "Greška";
		else
			s = "Грешка";
		
        Alert userNotSelectedAlert = new Alert(Alert.AlertType.ERROR);
        userNotSelectedAlert.setTitle(s);
        userNotSelectedAlert.setHeaderText(s + "!");
        userNotSelectedAlert.setContentText(poruka);
        userNotSelectedAlert.showAndWait();
    }
}
