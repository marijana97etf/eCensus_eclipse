package model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike;

public class ObrazovneKarakteristike {

    private PISMENOST pismenost;
    private INFORMATICKA_PISMENOST informatickaPismenost;
    private ZavrsenaSkola zavrsenaSkola;
    private PohadjanjeSkole pohadjanjeSkole;


    // CONSTRUCTOR

    public ObrazovneKarakteristike(PISMENOST pismenost, INFORMATICKA_PISMENOST informatickaPismenost, ZavrsenaSkola zavrsenaSkola, PohadjanjeSkole pohadjanjeSkole) {
        this.pismenost = pismenost;
        this.informatickaPismenost = informatickaPismenost;
        this.zavrsenaSkola = zavrsenaSkola;
        this.pohadjanjeSkole = pohadjanjeSkole;
    }


    // SETTERS AND GETTERS

    public PISMENOST getPismenost() {
        return pismenost;
    }

    public void setPismenost(PISMENOST pismenost) {
        this.pismenost = pismenost;
    }

    public INFORMATICKA_PISMENOST getInformatickaPismenost() {
        return informatickaPismenost;
    }

    public void setInformatickaPismenost(INFORMATICKA_PISMENOST informatickaPismenost) {
        this.informatickaPismenost = informatickaPismenost;
    }

    public ZavrsenaSkola getZavrsenaSkola() {
        return zavrsenaSkola;
    }

    public void setZavrsenaSkola(ZavrsenaSkola zavrsenaSkola) {
        this.zavrsenaSkola = zavrsenaSkola;
    }

    public PohadjanjeSkole getPohadjanjeSkole() {
        return pohadjanjeSkole;
    }

    public void setPohadjanjeSkole(PohadjanjeSkole pohadjanjeSkole) {
        this.pohadjanjeSkole = pohadjanjeSkole;
    }

    @Override
    public String toString() {
        return "ObrazovneKarakteristike{" +
                "pismenost=" + pismenost +
                ", informatickaPismenost=" + informatickaPismenost +
                ", zavrsenaSkola=" + zavrsenaSkola +
                ", pohadjanjeSkole=" + pohadjanjeSkole +
                '}';
    }
}
