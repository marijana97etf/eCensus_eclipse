package model.pracenje_popisa.statisticki_izvjestaji.prema_obiljezjima_nacije;

import model.pracenje_popisa.statisticki_izvjestaji.StatistickiIzvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema nacionalnosti, vjeroispovjesti i maternjem jeziku.
 */
public abstract class SI_PremaObiljezjimaNacije extends StatistickiIzvjestaj {
    public SI_PremaObiljezjimaNacije(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }
}
