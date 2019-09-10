package model.pracenje_popisa.statisticki_izvjestaji.prema_domacinstvima;

import model.pracenje_popisa.statisticki_izvjestaji.StatistickiIzvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema domaćinstvima.
 */
public abstract class SI_PremaDomacinstvima extends StatistickiIzvjestaj {
    public SI_PremaDomacinstvima(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }
}
