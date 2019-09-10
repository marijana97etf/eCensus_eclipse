package controller.KontrolerZaJezikeIPisma;

import org.javatuples.Triplet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class KontrolerZaJezik {

    private List<Triplet<String, String, String>> list;

    private HashMap<String,String> latinCirillic;

    public KontrolerZaJezik()
    {
        //readSentencesDictionary();
        readLatinToCyrillicDictionary();
    }

    private boolean readLatinToCyrillicDictionary() {
        List<String> lines = new ArrayList<>();
        try {
            Path path = Paths.get("src\\files\\lat_to_cyr_dictionary.txt");
            lines = Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        latinCirillic = new HashMap<>();

        for(String line : lines)
        {
            String[] letters = line.split(" <=> ");
            latinCirillic.put(letters[0], letters[1]);
        }

        if(lines.size()==0) return false;
        return true;
    }

    public boolean readSentencesDictionary()
    {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get("/src/files/dictionary.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        list = lines
                .stream()
                .map(e->
                {
                    String[] words = e.split(";");
                    return new Triplet<>(words[0], words[1], words[2]);
                }).collect(Collectors.toList());

        if(lines.size()==0) return false;
        return true;
    }

    public List<Triplet<String, String, String>> getList() {
        return list;
    }

    public void setList(List<Triplet<String, String, String>> list) {
        this.list = list;
    }

    public String latinToCyrillic(String sentence)
    {
        String tmp = new String(sentence.toCharArray());
        for(var entry : latinCirillic.entrySet())
        {
            tmp = tmp.replaceAll(entry.getKey(), entry.getValue());
        }
        return tmp;
    }
}
