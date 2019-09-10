package model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu;

import model.pracenje_popisa.statisticki_podaci.PodaciODomacinstvu;
import model.pracenje_popisa.statisticki_podaci.podaci_o_stanu.PodaciOStanu;


public abstract class PodaciONeporodicnomDomacinstvu extends PodaciODomacinstvu {

    // CONSTRUCTOR


    public PodaciONeporodicnomDomacinstvu(OSNOV_KORISTENJA_STANA osnovKoristenjaStana, PodaciOStanu podaciOStanu) {
        super(osnovKoristenjaStana, podaciOStanu);
    }
}
