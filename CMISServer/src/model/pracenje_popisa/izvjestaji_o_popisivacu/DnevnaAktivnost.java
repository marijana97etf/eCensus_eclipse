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


    // SETTERS AND GETTERS

    public long getBrojPopisanihStanovnika() {
        return obidjeniPopisniKrugovi.stream().mapToLong(e -> getBrojPopisnicaPopisnogKruga(e, PopisnicaOStanovniku.class)).reduce(0, (a, b) -> a + b);
    }

    public long getBrojPopisanihDomacinstava() {
        return obidjeniPopisniKrugovi.stream().mapToLong(e -> getBrojPopisnicaPopisnogKruga(e, PopisnicaODomacinstvu.class)).reduce(0, (a, b) -> a + b);
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


    // POMOCNE FUNKCIJE

    private long getBrojPopisnicaPopisnogKruga(PopisniKrug popisniKrug, Class klasa)
    {
        var count = popisniKrug.popisnice.stream().filter(klasa::isInstance).count();
        return count;
    }
}
