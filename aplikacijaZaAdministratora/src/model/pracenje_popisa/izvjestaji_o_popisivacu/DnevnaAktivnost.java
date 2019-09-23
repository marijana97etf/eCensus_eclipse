package model.pracenje_popisa.izvjestaji_o_popisivacu;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DnevnaAktivnost extends Aktivnost {

    protected LocalDate dan;
    protected Set<PopisniKrug> obidjeniPopisniKrugovi;

    public DnevnaAktivnost(LocalDate dan) {
        obidjeniPopisniKrugovi = new HashSet<>();
        this.dan = dan;
    }

    public LocalDate getDan() {
        return dan;
    }

    public void setDan(LocalDate dan) {
        this.dan = dan;
    }

    public Set<PopisniKrug> getObidjeniPopisniKrugovi() {
        return obidjeniPopisniKrugovi;
    }

    public void setObidjeniPopisniKrugovi(Set<PopisniKrug> obidjeniPopisniKrugovi) {
        this.obidjeniPopisniKrugovi = obidjeniPopisniKrugovi;
    }

}
