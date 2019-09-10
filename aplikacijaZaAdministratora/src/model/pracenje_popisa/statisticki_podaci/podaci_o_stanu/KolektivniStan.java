package model.pracenje_popisa.statisticki_podaci.podaci_o_stanu;

public class KolektivniStan implements JedinicaZaStanovanje {

    private TIP_KOLEKTIVNOG_STANA tip;

    public TIP_KOLEKTIVNOG_STANA getTip() {
        return tip;
    }

    public void setTip(TIP_KOLEKTIVNOG_STANA tip) {
        this.tip = tip;
    }


}
