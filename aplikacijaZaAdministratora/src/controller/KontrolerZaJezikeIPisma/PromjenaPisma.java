package controller.KontrolerZaJezikeIPisma;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PromjenaPisma {

	private static Map<String, String> rjecnikLatinicaUCirilicu = new HashMap<>();
	private static Map<String, String> rjecnikCirilicaULatinicu = new HashMap<>();
	
	static
	{
		procitajRjecnik("rjecnikLatinicaUCirilicu.txt", rjecnikLatinicaUCirilicu);
		procitajRjecnik("rjecnikCirilicaULatinicu.txt", rjecnikCirilicaULatinicu);
	}
	
	
	public static void promijeniPismo(String pismo) {
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
					String izmjena = pronadjiIZamijeni(l, pismo);
					noveLinije.add(izmjena);
				}
						
				prepisiFajl(file, noveLinije);	            
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static String pronadjiIZamijeni(String linija, String pismo) {
		String pattern = "ext=\"[^\"]*\"";
		Pattern myPattern = Pattern.compile(pattern);
		Matcher matcher = myPattern.matcher(linija);
		List<String> pronadjeno = new ArrayList<>();
		
		while (matcher.find()) {
		    pronadjeno.add(matcher.group());
		}

		String novaLinija = linija;
		for(String recenica : pronadjeno) {
			if(!"ext=\"true\"".equals(recenica)) {
				if(recenica.endsWith("?\"")) {
					String util = recenica.replace("?\"", "");
					String novaRecenica;
					if("ćirilica".equals(pismo))
						novaRecenica = zamijeniLatinicuCiricom(util.split("=")[1].replace("\"", ""));
					else
						novaRecenica = zamijeniCirilicuLatinicom(util.split("=")[1].replace("\"", ""));
					novaLinija = novaLinija.replaceAll(util, "ext=\"" + novaRecenica);
				}
				else {
					String novaRecenica;
					if("ćirilica".equals(pismo))
						novaRecenica = zamijeniLatinicuCiricom(recenica.split("=")[1].replace("\"", ""));
					else
						novaRecenica = zamijeniCirilicuLatinicom(recenica.split("=")[1].replace("\"", ""));
					novaLinija = novaLinija.replaceAll(recenica, "ext=\"" + novaRecenica + "\"");
				}
			}
		}			
		return novaLinija;
	}
	
	public static String zamijeniLatinicuCiricom(String recenica) {
		recenica = recenica.replaceAll("LJ", rjecnikLatinicaUCirilicu.get("LJ"));
		recenica = recenica.replaceAll("Lj", rjecnikLatinicaUCirilicu.get("Lj"));
		recenica = recenica.replaceAll("lj", rjecnikLatinicaUCirilicu.get("lj"));
		recenica = recenica.replaceAll("NJ", rjecnikLatinicaUCirilicu.get("NJ"));
		recenica = recenica.replaceAll("Nj", rjecnikLatinicaUCirilicu.get("Nj"));
		recenica = recenica.replaceAll("nj", rjecnikLatinicaUCirilicu.get("nj"));
		recenica = recenica.replaceAll("Dž", rjecnikLatinicaUCirilicu.get("Dž"));
		recenica = recenica.replaceAll("dž", rjecnikLatinicaUCirilicu.get("dž"));
		recenica = recenica.replaceAll("Š", "Ш");
		recenica = recenica.replaceAll("š", "ш");
		
		String tmp = new String(recenica.toCharArray());
        for(var entry : rjecnikLatinicaUCirilicu.entrySet())
        {
            tmp = tmp.replaceAll(entry.getKey(), entry.getValue());
        }
        return tmp;
	}
	
	public static String zamijeniCirilicuLatinicom(String recenica) {
		recenica = recenica.replaceAll("Љ", "Lj");
		recenica = recenica.replaceAll("љ", "lj");
		recenica = recenica.replaceAll("Њ", "Nj");
		recenica = recenica.replaceAll("њ", "nj");
		recenica = recenica.replaceAll("Џ", "Dž");
		recenica = recenica.replaceAll("џ", "dž");
		recenica = recenica.replaceAll("Ш", "Š");
		recenica = recenica.replaceAll("ш", "š");
		
		String tmp = new String(recenica.toCharArray());
        for(var entry : rjecnikCirilicaULatinicu.entrySet())
        {
            tmp = tmp.replaceAll(entry.getKey(), entry.getValue());
        }
        return tmp;
	}
	
	private static void prepisiFajl(File file, List<String> linije) throws IOException {
		BufferedWriter writer =
	             new BufferedWriter(new OutputStreamWriter(new FileOutputStream("src" + File.separator + "forme"  + File.separator + file.getName()), StandardCharsets.UTF_8));
		for(String l : linije) {
			writer.write(l);
			writer.newLine();
		}
	    writer.close();
	}
	
	private static void procitajRjecnik(String nazivRjecnika, Map<String, String> rjecnik) {
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
    }
}
