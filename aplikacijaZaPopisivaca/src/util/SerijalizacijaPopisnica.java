package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import model.Popisnica;
import model.PopisnicaZaStanovnika;

public class SerijalizacijaPopisnica {
	
	public static void serijalizujPopisnicu(Popisnica popisnica) {
		String tipPopisnica;
		if(popisnica instanceof PopisnicaZaStanovnika)
			tipPopisnica = "stanovnistvo";
		else
			tipPopisnica = "domacinstvo";
		File file = new File("." + File.separator + "sacuvanePopisnice" + File.separator + tipPopisnica + ".ser");
		try {
			List<Popisnica> popisnice = deserijalizujPopisnice(tipPopisnica);
			popisnice.add(popisnica);
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(popisnice);
			outputStream.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Popisnica> deserijalizujPopisnice(String tipPopisnica){
		List<Popisnica> popisnice = new ArrayList<>();
		
		File file = new File("." + File.separator + "sacuvanePopisnice" + File.separator + tipPopisnica + ".ser");
		if(file.exists()) {
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
				popisnice = (List<Popisnica>)inputStream.readObject();
				inputStream.close();
			}
			catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return popisnice;
	}
}
