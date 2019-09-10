package model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu;

import model.pracenje_popisa.statisticki_podaci.podaci_o_stanu.PodaciOStanu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PodaciOSamackomDomacinstvu extends PodaciONeporodicnomDomacinstvu {

    protected PojediniClanDomacinstva pojedinac;


    // CONSTRUCTOR

    public PodaciOSamackomDomacinstvu(OSNOV_KORISTENJA_STANA osnovKoristenjaStana,
                                      PodaciOStanu podaciOStanu,
                                      PojediniClanDomacinstva pojedinac)
    {
        super(osnovKoristenjaStana, podaciOStanu);
        this.pojedinac = pojedinac;
    }


    // SETTERS AND GETTERS

    @Override
    public List<ClanDomacinstva> getClanovi() {
        return Arrays.asList(new ClanDomacinstva[]{pojedinac});
    }
}
