package controller.KontrolerZaJezikeIPisma;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javatuples.Triplet;

import controller.kontroler_formi.KontrolerFormeZaPrijavu;
import javafx.scene.control.Alert;
import model.pracenje_popisa.JEZIK;

public class KontrolerZaPromjenuJezikaIPisma {
	private static List<Triplet<String, String, String>> rjecnik;

	static {
		List<String> lines = new ArrayList<>();
		rjecnik = new ArrayList<>();
        try {
            Path path = Paths.get("src" + File.separator + "resources" + File.separator + "rjecnikJezici.txt");
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(String line : lines){
        	rjecnik.add(new Triplet<String, String, String>(line.split("-")[0], line.split("-")[1], line.split("-")[2]));
        }
	};
	
	public static void prevedi() {
		String formeDirektorijum = "src" + File.separator + "view";
		File folder = new File(formeDirektorijum);
		File[] listOfFiles = folder.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				String lowercaseName = name.toLowerCase();
				if (lowercaseName.endsWith(".fxml")) {
					return true;
				} else {
					return false;
				}
			}
		});
		
		for(File file : listOfFiles) {
			try {
				byte[] bytes = new byte[(int)file.length()];
				DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(formeDirektorijum + File.separator + file.getName())));
				dataInputStream.readFully(bytes);           
				dataInputStream.close();      
				String original = new String(bytes, StandardCharsets.UTF_8);
										
				String izmjena = pronadjiIZamijeni(original);
						
				prepisiFajl(file, izmjena);	            
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String pronadjiIZamijeni(String linija) {
		String novaLinija = linija;

		for(Triplet<String, String, String> t : rjecnik) {
			String pattern;
			if(JEZIK.SRPSKI.equals(KontrolerFormeZaPrijavu.getTrenutniKorisnik().getJezik()))
				pattern = t.getValue0();
			else if(JEZIK.BOSANSKI.equals(KontrolerFormeZaPrijavu.getTrenutniKorisnik().getJezik()))
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
				if(JEZIK.SRPSKI.equals(KontrolerFormeZaPrijavu.getTrenutniKorisnik().getJezik()))
					novaRijec = t.getValue0();
				else if(JEZIK.BOSANSKI.equals(KontrolerFormeZaPrijavu.getTrenutniKorisnik().getJezik()))
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
	
	private static void prepisiFajl(File file, String sadrzaj) throws IOException {
		BufferedWriter writer =
	             new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src" + File.separator + "view" + File.separator + file.getName()), StandardCharsets.UTF_8));
		writer.write(sadrzaj);
	    writer.close();
	}
	
	public static void prikaziUpozorenje(String poruka){
    	String greska = "Greška";
    	if(JEZIK.SRPSKI.equals(KontrolerFormeZaPrijavu.getTrenutniKorisnik().getJezik())) {
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
