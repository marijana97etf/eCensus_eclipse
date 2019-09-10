package model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public class Porodica implements ClanDomacinstva {
    private TIP_PORODICE tip;
    private Collection<PojediniClanDomacinstva> clanoviPorodice;

    // CONSTRUCTOR

    public Porodica(TIP_PORODICE tip, Collection<PojediniClanDomacinstva> clanoviPorodice) {
        this.tip = tip;
        this.clanoviPorodice = clanoviPorodice;
    }


    // GETTERS AND SETTERS

    public TIP_PORODICE getTip() {
        return tip;
    }

    public void setTip(TIP_PORODICE tip) {
        this.tip = tip;
    }

    public Collection<PojediniClanDomacinstva> getClanoviPorodice() {
        return clanoviPorodice;
    }

    public void setClanoviPorodice(Collection<PojediniClanDomacinstva> clanoviPorodice) {
        this.clanoviPorodice = clanoviPorodice;
    }

    // METHODS

    public Stream<PojediniClanDomacinstva> getClanovi()
    {
        return new ArrayList<>(clanoviPorodice).stream();
    }

    @Override
    public String toString() {
        return "Porodica{" +
                "tip=" + tip +
                ", clanoviPorodice=" + clanoviPorodice +
                '}';
    }
}
