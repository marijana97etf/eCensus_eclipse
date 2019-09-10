package model.pracenje_popisa.statisticki_podaci;

import java.util.HashMap;

public class AgregatniPodaci extends HashMap<String,Number> implements StatistickiPodaci {
    @Override
    public String toString() {
        var tmp = new Object(){ String s=""; };
        entrySet().forEach(entry-> tmp.s += (entry.getKey() + " = " + entry.getValue() + System.lineSeparator()));
        return tmp.s;
    }
}
