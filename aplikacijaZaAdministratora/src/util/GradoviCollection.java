package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GradoviCollection {

    private static List<String> gradovi;

    static {
        try {
            setGradovi(Files.readAllLines(Paths.get("./resources/gradovi.txt")));
            String[] array = gradovi.toArray(new String[gradovi.size()]);
            for(int i=0; i<gradovi.size(); i++)
                array[i] = PromjenaPisma.zamijeniCirilicuLatinicom(array[i]);
            setGradovi(Arrays.asList(array));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Collection<String> getGradovi() {
        return gradovi;
    }

    private static void setGradovi(List<String> gradovi) {
        GradoviCollection.gradovi = gradovi;
    }

    private GradoviCollection() {}

    public static int getID(String grad)
    {
        return gradovi.indexOf(grad);
    }
}
