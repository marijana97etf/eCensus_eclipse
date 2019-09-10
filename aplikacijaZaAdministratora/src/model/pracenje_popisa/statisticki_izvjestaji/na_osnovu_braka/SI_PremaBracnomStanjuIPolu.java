package model.pracenje_popisa.statisticki_izvjestaji.na_osnovu_braka;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj prema zakonskom bračnom stanju i polu.
 */
public class SI_PremaBracnomStanjuIPolu extends SI_PremaBracnomStatusu {
    public SI_PremaBracnomStanjuIPolu(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        return null;
    }
}
