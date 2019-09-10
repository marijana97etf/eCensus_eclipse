package test;

import model.pracenje_popisa.statisticki_podaci.AgregatniPodaci;
import model.pracenje_popisa.statisticki_podaci.PodaciOStanovniku;
import model.pracenje_popisa.statisticki_podaci.PopisaniPodaci;
import model.pracenje_popisa.statisticki_podaci.StatistickiPodaci;
import model.pracenje_popisa.statisticki_podaci.bracni_status_fertilitet_zena.BRACNO_STANJE;
import model.pracenje_popisa.statisticki_podaci.bracni_status_fertilitet_zena.BracniStatusIFertilitetZena;
import model.pracenje_popisa.statisticki_podaci.bracni_status_fertilitet_zena.FERTILITET_ZENA;
import model.pracenje_popisa.statisticki_podaci.ekonomske_karakteristike.*;
import model.pracenje_popisa.statisticki_podaci.obiljezja_nacije.MATERNJI_JEZIK;
import model.pracenje_popisa.statisticki_podaci.obiljezja_nacije.NACIONALNOST;
import model.pracenje_popisa.statisticki_podaci.obiljezja_nacije.NacionalnaObiljezja;
import model.pracenje_popisa.statisticki_podaci.obiljezja_nacije.VJEROISPOVIJEST;
import model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike.*;
import model.pracenje_popisa.statisticki_podaci.starost_pol.POL;
import model.pracenje_popisa.statisticki_podaci.starost_pol.StarostIPol;

import java.util.Map;

public class TestStatistickiPodaci {
    public static void main(String... args)
    {
        Map podaci = new AgregatniPodaci();
        podaci.put("Broj ljudi", 5);
        podaci.put("Broj stanova", 10);
        podaci.put("Prosjek broja godina", 44.5);

        System.out.println(podaci);

        StatistickiPodaci popisaniPodaci = new PodaciOStanovniku(
                "Zoran",
                "Mrkonjic",
                "32443",
                new StarostIPol(30, POL.MUSKI),
                new BracniStatusIFertilitetZena(BRACNO_STANJE.U_BRAKU, null),
                new NacionalnaObiljezja(NACIONALNOST.SRBIN, VJEROISPOVIJEST.PRAVOSLAVNA, MATERNJI_JEZIK.SRPSKI),
                new EkonomskeKarakteristike(
                        ZANIMANJE.TEHNICARI_STRUCNI_SARADNICI,
                        DJELATNOST.STRUCNE_NAUCNE_I_TEHNICKE_DJELATNOSTI,
                        new ZaposlenaRadnaSnaga(),
                        ZAPOSLENOST.ZAPOSLEN),
                new ObrazovneKarakteristike(
                        PISMENOST.PISMEN,
                        INFORMATICKA_PISMENOST.PISMENA_OSOBA,
                        new ZavrseniFakultet(4, new ProgramPoBolonji(TIP_STUDIJE_PO_PROGRAMU_PO_BOLONJI.PRVI_CIKLUS)),
                        new PohadjanjeFakulteta(new ProgramPoBolonji(TIP_STUDIJE_PO_PROGRAMU_PO_BOLONJI.DRUGI_CIKLUS))));
        System.out.println(popisaniPodaci);
    }
}
