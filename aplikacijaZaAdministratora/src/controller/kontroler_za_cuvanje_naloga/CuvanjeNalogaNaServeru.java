package controller.kontroler_za_cuvanje_naloga;

import controller.kontroler_za_cuvanje_naloga.CuvanjeNaloga;
import model.korisnicki_nalozi.SkladisteNaloga;

public class CuvanjeNalogaNaServeru implements CuvanjeNaloga {
    @Override
    public boolean sacuvajNaloge(SkladisteNaloga skladisteNaloga) {
        return false;
    }

    @Override
    public SkladisteNaloga ucitajSacuvaneNaloge() {
        return null;
    }
}
