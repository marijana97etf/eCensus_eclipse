package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;

public class OpstineCollection {

	private static Collection<String> opstine;
	
	static {
		try {
			setOpstine(Files.readAllLines(Paths.get("./resources/opstine.txt")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Collection<String> getOpstine() {
		return opstine;
	}

	private static void setOpstine(Collection<String> opstine) {
		OpstineCollection.opstine = opstine;
	}
	
	private OpstineCollection() {}
}
