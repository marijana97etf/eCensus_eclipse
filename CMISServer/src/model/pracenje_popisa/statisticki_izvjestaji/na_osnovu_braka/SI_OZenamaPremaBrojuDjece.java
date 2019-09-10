package model.pracenje_popisa.statisticki_izvjestaji.na_osnovu_braka;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statisticki izvjestaj o ženama prema ukupnom broju živorođene djece.
 */
public class SI_OZenamaPremaBrojuDjece extends SI_PremaBracnomStatusu {
    public SI_OZenamaPremaBrojuDjece(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        return null;
    }
}
