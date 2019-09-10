package model.pracenje_popisa.statisticki_izvjestaji.prema_obiljezjima_nacije;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema vjeroispovijesti i polu.
 */
public class SI_PremaVjeroispovjestiIPolu extends SI_PremaObiljezjimaNacije {
    public SI_PremaVjeroispovjestiIPolu(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO
        return null;
    }
}
