package model.pracenje_popisa.izvjestaji_o_popisivacu;

import model.pracenje_popisa.Izvjestaj;

public class IzvjestajOPopisivacu implements Izvjestaj {
    protected CjelokupnaAktivnost cjelokupnaAktivnost;

    @Override
    public Izvjestaj kreirajIzvjestaj() {
        //TODO
        return null;
    }



    // SETTERS AND GETTERS

    public CjelokupnaAktivnost getCjelokupnaAktivnost() {
        return cjelokupnaAktivnost;
    }

    public void setCjelokupnaAktivnost(CjelokupnaAktivnost cjelokupnaAktivnost) {
        this.cjelokupnaAktivnost = cjelokupnaAktivnost;
    }
}
