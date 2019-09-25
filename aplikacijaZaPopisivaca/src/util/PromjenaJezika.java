package util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.javatuples.Triplet;

import main.Main;

public class PromjenaJezika {
	private static List<Triplet<String, String, String>> rjecnik;
	
	public PromjenaJezika() {
		procitajRjecnik();
	}
	
	public static String pronadjiIZamijeni(String linija, String odabraniJezik) {
		String novaLinija = linija;
		if(rjecnik == null)
			procitajRjecnik();
	
		for(Triplet<String, String, String> t : rjecnik) {
			String pattern;
			if("Ñ�Ñ€Ð¿Ñ�ÐºÐ¸".equals(Main.trenutniJezik))
				pattern = t.getValue0();
			else if("boÅ¡njaÄ�ki".equals(Main.trenutniJezik))
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
				else if("boÅ¡njaÄ�ki".equals(odabraniJezik) || "Ð±Ð¾ÑˆÑšÐ°Ñ‡ÐºÐ¸".equals(odabraniJezik))
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
	
	private static void procitajRjecnik() {
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
}
