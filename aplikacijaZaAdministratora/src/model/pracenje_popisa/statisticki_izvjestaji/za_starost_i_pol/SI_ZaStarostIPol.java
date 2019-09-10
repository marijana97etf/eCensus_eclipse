package model.pracenje_popisa.statisticki_izvjestaji.za_starost_i_pol;

import model.pracenje_popisa.statisticki_izvjestaji.StatistickiIzvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj za starost i pol.
 */
public abstract class SI_ZaStarostIPol extends StatistickiIzvjestaj
{
    public SI_ZaStarostIPol(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    public SI_ZaStarostIPol() {
        super();
    }
}