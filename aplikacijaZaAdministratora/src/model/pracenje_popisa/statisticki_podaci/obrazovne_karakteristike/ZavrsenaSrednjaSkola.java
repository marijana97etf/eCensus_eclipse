package model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike;

public class ZavrsenaSrednjaSkola extends ZavrsenaSkola {
    private VRSTA_SREDNJE_SKOLE vrsta;


    // CONSTRUCTOR

    public ZavrsenaSrednjaSkola(Integer duzinaTrajanja) {
        super(duzinaTrajanja);
    }


    // SETTERS AND GETTERS

    public VRSTA_SREDNJE_SKOLE getVrsta() {
        return vrsta;
    }

    public void setVrsta(VRSTA_SREDNJE_SKOLE vrsta) {
        this.vrsta = vrsta;
    }
}
