package model.korisnicki_nalozi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SkladisteNaloga extends ArrayList<KorisnikSistema> implements Serializable {
    public SkladisteNaloga(KorisnikSistema... korisniciSistema) {
        super(Arrays.asList(korisniciSistema));
    }
}
