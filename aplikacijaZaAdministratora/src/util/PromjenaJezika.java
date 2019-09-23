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
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class PromjenaJezika {
	private static List<Triplet<String, String, String>> rjecnik;
	
	static {
		procitajRjecnik();
	}
	
	public static String pronadjiIZamijeni(String linija, JEZIK jezik, PISMO pismo) {
		String novaLinija = linija;

		for(Triplet<String, String, String> t : rjecnik) {
			String pattern;
			if(JEZIK.SRPSKI.equals(jezik))
			{
				if(PISMO.CIRILICA.equals(pismo))
					pattern = PromjenaPisma.zamijeniLatinicuCiricom(t.getValue0());
				else
					pattern = t.getValue0();
			}
			else if(JEZIK.BOSANSKI.equals(jezik))
				pattern = t.getValue1();
			else
				pattern = t.getValue2();
			
			Pattern myPattern = Pattern.compile(pattern);
			Matcher matcher = myPattern.matcher(linija);
			List<String> pronadjeno = new ArrayList<>();
			
			while (matcher.find()) {
			    pronadjeno.add(matcher.group());
			}
	
			for(String rijec : pronadjeno) {
				String novaRijec;
				if(JEZIK.SRPSKI.equals(jezik))
					novaRijec = t.getValue0();
				else if(JEZIK.BOSANSKI.equals(jezik))
					novaRijec = t.getValue1();
				else
					novaRijec = t.getValue2();
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
