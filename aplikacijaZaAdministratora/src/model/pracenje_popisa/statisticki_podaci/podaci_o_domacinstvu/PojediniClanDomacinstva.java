package model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu;

import model.pracenje_popisa.statisticki_podaci.PodaciOStanovniku;

public abstract class PojediniClanDomacinstva implements ClanDomacinstva
{
    protected PodaciOStanovniku podaci;


    // CONSTRUCTOR

    public PojediniClanDomacinstva(PodaciOStanovniku podaci) {
        this.podaci = podaci;
    }


    // SETTERS AND GETTERS

    public PodaciOStanovniku getPodaci() {
        return podaci;
    }

    public void setPodaci(PodaciOStanovniku podaci) {
        this.podaci = podaci;
    }

    @Override
    public String toString() {
        return "PojediniClanDomacinstva{" +
                "podaci=" + podaci +
                '}';
    }
}
