package model.pracenje_popisa.statisticki_izvjestaji.prema_obrazovnim_karakteristikama;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema starosti preko deset godina prema kompjuterskoj pismenosti i polu.
 */
public class SI_PremaKompPismenostiIPolu extends SI_PremaObrazovanju {
    public SI_PremaKompPismenostiIPolu(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO
        return null;
    }
}
