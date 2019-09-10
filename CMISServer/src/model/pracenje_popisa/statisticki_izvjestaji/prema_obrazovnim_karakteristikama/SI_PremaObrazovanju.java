package model.pracenje_popisa.statisticki_izvjestaji.prema_obrazovnim_karakteristikama;

import model.pracenje_popisa.statisticki_izvjestaji.StatistickiIzvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema obrazovnim karakteristikama
 */
public abstract class SI_PremaObrazovanju extends StatistickiIzvjestaj {
    public SI_PremaObrazovanju(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }
}
