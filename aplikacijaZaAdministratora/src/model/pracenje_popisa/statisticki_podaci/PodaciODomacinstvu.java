package model.pracenje_popisa.statisticki_podaci;

import model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu.ClanDomacinstva;
import model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu.OSNOV_KORISTENJA_STANA;
import model.pracenje_popisa.statisticki_podaci.podaci_o_stanu.PodaciOStanu;

import java.util.List;
import java.util.stream.Stream;

public abstract class PodaciODomacinstvu implements PopisaniPodaci {

    protected OSNOV_KORISTENJA_STANA osnovKoristenjaStana;
    protected PodaciOStanu podaciOStanu;

    // CONSTRUCTOR

    public PodaciODomacinstvu(OSNOV_KORISTENJA_STANA osnovKoristenjaStana, PodaciOStanu podaciOStanu) {
        this.osnovKoristenjaStana = osnovKoristenjaStana;
        this.podaciOStanu = podaciOStanu;
    }


    // SETTERS AND GETTERS

    public OSNOV_KORISTENJA_STANA getOsnovKoristenjaStana() {
        return osnovKoristenjaStana;
    }

    public void setOsnovKoristenjaStana(OSNOV_KORISTENJA_STANA osnovKoristenjaStana) {
        this.osnovKoristenjaStana = osnovKoristenjaStana;
    }

    // ABSTRACT METHOD

    public abstract List<ClanDomacinstva> getClanovi();


    // OTHER METHODS

    public Stream<ClanDomacinstva> getStreamOfClanovi()
    {
        return getClanovi().stream();
    }

    public PodaciOStanu getPodaciOStanu() {
        return podaciOStanu;
    }

    public void setPodaciOStanu(PodaciOStanu podaciOStanu) {
        this.podaciOStanu = podaciOStanu;
    }
}
