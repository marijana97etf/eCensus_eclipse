package model.pracenje_popisa.statisticki_izvjestaji;

import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;

public abstract class StatistickiIzvjestaj implements Izvjestaj
{
    private StatistickiPodaci[] statistickiPodaci;

    public StatistickiIzvjestaj(StatistickiPodaci[] statistickiPodaci) {
        this.statistickiPodaci = statistickiPodaci;
    }

    public StatistickiIzvjestaj() {

    }

    public StatistickiPodaci[] getStatistickiPodaci() {
        return statistickiPodaci;
    }

    public void setStatistickiPodaci(StatistickiPodaci[] statistickiPodaci) {
        this.statistickiPodaci = statistickiPodaci;
    }
};

