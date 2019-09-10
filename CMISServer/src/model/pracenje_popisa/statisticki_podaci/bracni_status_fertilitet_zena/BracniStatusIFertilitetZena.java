package model.pracenje_popisa.statisticki_podaci.bracni_status_fertilitet_zena;

public class BracniStatusIFertilitetZena {

    private BRACNO_STANJE bracnoStanje;
    private FERTILITET_ZENA fertilitetZena;

    // CONSTRUCTOR

    public BracniStatusIFertilitetZena(BRACNO_STANJE bracnoStanje, FERTILITET_ZENA fertilitetZena) {
        this.bracnoStanje = bracnoStanje;
        this.fertilitetZena = fertilitetZena;
    }


    // SETTERS AND GETTERS


    public BRACNO_STANJE getBracnoStanje() {
        return bracnoStanje;
    }

    public void setBracnoStanje(BRACNO_STANJE bracnoStanje) {
        this.bracnoStanje = bracnoStanje;
    }

    @Override
    public String toString() {
        return "BracniStatusIFertilitetZena{" +
                "bracnoStanje=" + bracnoStanje +
                ", fertilitetZena=" + fertilitetZena +
                '}';
    }
}
