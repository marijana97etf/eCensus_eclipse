package model.pracenje_popisa.statisticki_izvjestaji.prema_obrazovnim_karakteristikama;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema starosti preko deset godina prema pismenosti i polu.
 */
public class SI_PremaPismenostiIPolu extends SI_PremaObrazovanju {
    public SI_PremaPismenostiIPolu(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO
        return null;
    }
}
