package model.pracenje_popisa.izvjestaji_o_popisivacu;

import java.util.HashSet;
import java.util.Set;

public class PopisniKrug {

    protected Set<Popisnica> popisnice;

    public PopisniKrug() {
        popisnice = new HashSet<>();
    }



    // SETTERS AND GETTERS

    public Set<Popisnica> getPopisnice() {
        return popisnice;
    }

    public void setPopisnice(Set<Popisnica> popisnice) {
        this.popisnice = popisnice;
    }

    public boolean dodajPopisnicu(Popisnica popisnica)
    {
        return popisnice.add(popisnica);
    }
}
