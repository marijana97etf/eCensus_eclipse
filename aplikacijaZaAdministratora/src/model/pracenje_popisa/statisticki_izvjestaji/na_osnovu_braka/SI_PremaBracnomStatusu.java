package model.pracenje_popisa.statisticki_izvjestaji.na_osnovu_braka;

import model.pracenje_popisa.statisticki_izvjestaji.StatistickiIzvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema bračnom statusu i fertilitetu ženskog stanovništva.
 */
public abstract class SI_PremaBracnomStatusu extends StatistickiIzvjestaj {
    public SI_PremaBracnomStatusu(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }
}
