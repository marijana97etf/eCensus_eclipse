package model.pracenje_popisa.statisticki_izvjestaji.prema_ekonomskim_karakteristikama;

import model.pracenje_popisa.statisticki_izvjestaji.StatistickiIzvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema ekonomskim karakteristikama
 */
public abstract class SI_PremaEkonomskimKarakteristikama extends StatistickiIzvjestaj {
    public SI_PremaEkonomskimKarakteristikama(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }
}
