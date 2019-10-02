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
import model.PopisnicaZaDomacinstvo;
import model.PopisnicaZaStanovnika;

public class SerijalizacijaPopisnica {
	
	public static void serijalizujPopisniceZaStanovnika(List<PopisnicaZaStanovnika> popisnice) {
		File file = new File("." + File.separator + "sacuvanePopisnice" + File.separator + "stanovnistvo" + ".ser");
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(popisnice);
			outputStream.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void serijalizujPopisniceZaDomacinstvo(List<PopisnicaZaDomacinstvo> popisnice) {
		File file = new File("." + File.separator + "sacuvanePopisnice" + File.separator + "domacinstvo" + ".ser");
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));
			outputStream.writeObject(popisnice);
			outputStream.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static List<PopisnicaZaStanovnika> deserijalizujPopisniceZaStanovnika(){
		List<PopisnicaZaStanovnika> popisnice = new ArrayList<>();
		
		File file = new File("." + File.separator + "sacuvanePopisnice" + File.separator + "stanovnistvo" + ".ser");
		if(file.exists()) {
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
				popisnice = (List<PopisnicaZaStanovnika>)inputStream.readObject();
				inputStream.close();
			}
			catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return popisnice;
	}
	
	public static List<PopisnicaZaDomacinstvo> deserijalizujPopisniceZaDomacinstvo(){
		List<PopisnicaZaDomacinstvo> popisnice = new ArrayList<>();
		
		File file = new File("." + File.separator + "sacuvanePopisnice" + File.separator + "domacinstvo" + ".ser");
		if(file.exists()) {
			try {
				ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));
				popisnice = (List<PopisnicaZaDomacinstvo>)inputStream.readObject();
				inputStream.close();
			}
			catch(IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		return popisnice;
	}
}
