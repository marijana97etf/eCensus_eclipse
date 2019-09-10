package model.pracenje_popisa.statisticki_izvjestaji.prema_domacinstvima;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema domaćinstvima koja obavljaju poljoprivrednu aktivnost.
 */
public class SI_PremaPoljoprivredi extends SI_PremaDomacinstvima {
    public SI_PremaPoljoprivredi(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO: kreiranje izvjestaja
        return null;
    }
}
