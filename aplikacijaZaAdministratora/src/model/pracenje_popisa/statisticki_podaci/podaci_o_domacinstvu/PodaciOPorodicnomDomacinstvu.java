package model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu;

import model.pracenje_popisa.statisticki_podaci.PodaciODomacinstvu;
import model.pracenje_popisa.statisticki_podaci.podaci_o_stanu.PodaciOStanu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PodaciOPorodicnomDomacinstvu extends PodaciODomacinstvu {
    protected Collection<Porodica> porodice;


    // CONSTRUCTOR

    public PodaciOPorodicnomDomacinstvu(OSNOV_KORISTENJA_STANA osnovKoristenjaStana,
                                        PodaciOStanu podaciOStanu,
                                        Collection<Porodica> porodice)
    {
        super(osnovKoristenjaStana, podaciOStanu);
        this.porodice = porodice;
    }


    // SETTERS AND GETTERS

    @Override
    public List<ClanDomacinstva> getClanovi() {
        return porodice.stream()
                        .flatMap(Porodica::getClanovi)
                        .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "PodaciOPorodicnomDomacinstvu{" +
                "porodice=" + porodice +
                ", osnovKoristenjaStana=" + osnovKoristenjaStana +
                ", podaciOStanu=" + podaciOStanu +
                '}';
    }
}
