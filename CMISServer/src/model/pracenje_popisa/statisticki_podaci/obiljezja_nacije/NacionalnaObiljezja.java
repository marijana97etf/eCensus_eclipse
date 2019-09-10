package model.pracenje_popisa.statisticki_podaci.obiljezja_nacije;

public class NacionalnaObiljezja
{
    private NACIONALNOST nacionalnost;
    private VJEROISPOVIJEST vjeroispovijest;
    private MATERNJI_JEZIK maternjiJezik;

    // CONSTRUCTOR

    public NacionalnaObiljezja(NACIONALNOST nacionalnost, VJEROISPOVIJEST vjeroispovijest, MATERNJI_JEZIK maternjiJezik) {
        this.nacionalnost = nacionalnost;
        this.vjeroispovijest = vjeroispovijest;
        this.maternjiJezik = maternjiJezik;
    }


    // SETTERS AND GETTERS


    public NACIONALNOST getNacionalnost() {
        return nacionalnost;
    }

    public void setNacionalnost(NACIONALNOST nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public VJEROISPOVIJEST getVjeroispovijest() {
        return vjeroispovijest;
    }

    public void setVjeroispovijest(VJEROISPOVIJEST vjeroispovijest) {
        this.vjeroispovijest = vjeroispovijest;
    }

    public MATERNJI_JEZIK getMaternjiJezik() {
        return maternjiJezik;
    }

    public void setMaternjiJezik(MATERNJI_JEZIK maternjiJezik) {
        this.maternjiJezik = maternjiJezik;
    }

    @Override
    public String toString() {
        return "NacionalnaObiljezja{" +
                "nacionalnost=" + nacionalnost +
                ", vjeroispovijest=" + vjeroispovijest +
                ", maternjiJezik=" + maternjiJezik +
                '}';
    }
}
