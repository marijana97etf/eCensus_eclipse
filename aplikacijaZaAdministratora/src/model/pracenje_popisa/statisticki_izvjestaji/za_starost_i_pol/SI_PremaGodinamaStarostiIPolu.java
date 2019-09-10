package model.pracenje_popisa.statisticki_izvjestaji.za_starost_i_pol;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;


/**
 * Statistički izvještaj prema pojedinačnim godinama starosti i polu.
 */
public class SI_PremaGodinamaStarostiIPolu extends SI_ZaStarostIPol {
    public SI_PremaGodinamaStarostiIPolu(StatistickiPodaci[] statistickiPodaci) {
        super(statistickiPodaci);
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO
        return null;
    }
}
