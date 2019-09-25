package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;

public class OpstineCollection {

	private static List<String> opstine;
	
	static {
		try {
			setOpstine(Files.readAllLines(Paths.get("./resources/opstine.txt")));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Collection<String> getOpstine() {
		return opstine;
	}

	private static void setOpstine(List<String> opstine) {
		OpstineCollection.opstine = opstine;
	}
	
	private OpstineCollection() {}

	public static int getID(String opstina)
	{
		return opstine.indexOf(opstina);
	}
}
