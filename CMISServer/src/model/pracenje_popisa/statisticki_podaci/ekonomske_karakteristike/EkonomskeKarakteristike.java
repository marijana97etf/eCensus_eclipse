package model.pracenje_popisa.statisticki_podaci.ekonomske_karakteristike;

public class EkonomskeKarakteristike {
    private ZANIMANJE zanimanje;
    private DJELATNOST djelatnost;
    private RadnaAktivnost radnaAktivnost;
    private ZAPOSLENOST zaposlenost;


    // CONSTRUCTOR

    public EkonomskeKarakteristike(ZANIMANJE zanimanje, DJELATNOST djelatnost, RadnaAktivnost radnaAktivnost, ZAPOSLENOST zaposlenost) {
        this.zanimanje = zanimanje;
        this.djelatnost = djelatnost;
        this.radnaAktivnost = radnaAktivnost;
        this.zaposlenost = zaposlenost;
    }



    // SETTERS AND GETTERS

    public ZANIMANJE getZanimanje() {
        return zanimanje;
    }

    public void setZanimanje(ZANIMANJE zanimanje) {
        this.zanimanje = zanimanje;
    }

    public DJELATNOST getDjelatnost() {
        return djelatnost;
    }

    public void setDjelatnost(DJELATNOST djelatnost) {
        this.djelatnost = djelatnost;
    }

    public RadnaAktivnost getRadnaAktivnost() {
        return radnaAktivnost;
    }

    public void setRadnaAktivnost(RadnaAktivnost radnaAktivnost) {
        this.radnaAktivnost = radnaAktivnost;
    }

    public ZAPOSLENOST getZaposlenost() {
        return zaposlenost;
    }

    public void setZaposlenost(ZAPOSLENOST zaposlenost) {
        this.zaposlenost = zaposlenost;
    }

    @Override
    public String toString() {
        return "EkonomskeKarakteristike{" +
                "zanimanje=" + zanimanje +
                ", djelatnost=" + djelatnost +
                ", radnaAktivnost=" + radnaAktivnost +
                ", zaposlenost=" + zaposlenost +
                '}';
    }
}
