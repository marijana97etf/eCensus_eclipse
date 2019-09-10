package model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu;

import model.pracenje_popisa.statisticki_podaci.podaci_o_stanu.PodaciOStanu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PodaciOViseclanomNeporodicnomDomacinstvu extends PodaciONeporodicnomDomacinstvu {

    protected Collection<PojediniClanDomacinstva> clanoviDomacinstva;


    // CONSTRUCTOR

    public PodaciOViseclanomNeporodicnomDomacinstvu(OSNOV_KORISTENJA_STANA osnovKoristenjaStana,
                                                    PodaciOStanu podaciOStanu,
                                                    Collection<PojediniClanDomacinstva> clanoviDomacinstva)
    {
        super(osnovKoristenjaStana, podaciOStanu);
        this.clanoviDomacinstva = clanoviDomacinstva;
    }


    // METHODS

    @Override
    public List<ClanDomacinstva> getClanovi()
    {
        return new ArrayList<>(clanoviDomacinstva);
    }
}
