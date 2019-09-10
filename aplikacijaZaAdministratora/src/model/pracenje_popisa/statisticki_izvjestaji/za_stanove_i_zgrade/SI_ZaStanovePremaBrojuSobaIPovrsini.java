package model.pracenje_popisa.statisticki_izvjestaji.za_stanove_i_zgrade;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

/**
 * Statistički izvještaj za stanove prema broju soba i površini.
 */
public class SI_ZaStanovePremaBrojuSobaIPovrsini extends SI_ZaStanoveIZgrade {
    public SI_ZaStanovePremaBrojuSobaIPovrsini(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO
        return null;
    }
}
