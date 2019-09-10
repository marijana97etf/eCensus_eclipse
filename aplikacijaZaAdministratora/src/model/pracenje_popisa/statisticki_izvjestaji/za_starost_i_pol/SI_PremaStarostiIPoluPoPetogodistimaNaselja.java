package model.pracenje_popisa.statisticki_izvjestaji.za_starost_i_pol;

import model.pracenje_popisa.Izvjestaj;

/**
 * Statistički izvještaj prema starosti i polu po petogodistima naselja.
 */
public class SI_PremaStarostiIPoluPoPetogodistimaNaselja extends SI_ZaStarostIPol {
    protected String opstina;

    public SI_PremaStarostiIPoluPoPetogodistimaNaselja(String opstina) {
        super();
        this.opstina = opstina;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO
        return null;
    }
}
