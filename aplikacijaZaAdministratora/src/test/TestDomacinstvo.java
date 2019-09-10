package test;

import model.pracenje_popisa.statisticki_podaci.PodaciODomacinstvu;
import model.pracenje_popisa.statisticki_podaci.PodaciOStanovniku;
import model.pracenje_popisa.statisticki_podaci.PopisaniPodaci;
import model.pracenje_popisa.statisticki_podaci.podaci_o_domacinstvu.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestDomacinstvo {
    public static void main(String... args)
    {
        Collection<Porodica> porodice = new ArrayList<>();
        Collection<PojediniClanDomacinstva> pojediniClanoviDomacinstva = Arrays.asList(new PojediniClanDomacinstva[]
        {
                new DomaciClanDomacinstva(new PodaciOStanovniku("Milan", "Milanovic", "343")),
                new DomaciClanDomacinstva(new PodaciOStanovniku("Milos", "Milanovic", "342")),
                new DomaciClanDomacinstva(new PodaciOStanovniku("Kristina", "Milanovic", "322")),
                new ClanDomacinstvaIzInostranstva(new PodaciOStanovniku("Milivoj", "Petrusic", "23"))
        });
        Collection<PojediniClanDomacinstva> pojediniClanoviDomacinstva2 = Arrays.asList(new PojediniClanDomacinstva[]
        {
                new ClanDomacinstvaIzInostranstva(new PodaciOStanovniku("Srdjan", "Vujic", "343")),
                new DomaciClanDomacinstva(new PodaciOStanovniku("Ana", "Mihajlovic", "342"))
        });
        Collection<PojediniClanDomacinstva> viseclanoNeporodincnoDomacinstvo = Arrays.asList(new PojediniClanDomacinstva[]
                {
                        new DomaciClanDomacinstva(new PodaciOStanovniku("Milos", "Stojakovic", "434")),
                        new ClanDomacinstvaIzInostranstva(new PodaciOStanovniku("Andrej", "Nikolic", "989"))
                });
        PojediniClanDomacinstva samac = new DomaciClanDomacinstva(new PodaciOStanovniku("Shamso", "69", "69"));
        porodice.add(new Porodica(TIP_PORODICE.BRACNI_PAR_SA_DJECOM, pojediniClanoviDomacinstva));
        porodice.add(new Porodica(TIP_PORODICE.VANBRACNI_PAR_BEZ_DJECE, pojediniClanoviDomacinstva2));
        PodaciODomacinstvu[] popisaniPodaci = new PodaciODomacinstvu[]
                {
                        new PodaciOPorodicnomDomacinstvu(OSNOV_KORISTENJA_STANA.VLASNIK, null, porodice),
                        new PodaciOViseclanomNeporodicnomDomacinstvu(OSNOV_KORISTENJA_STANA.VLASNIK, null, viseclanoNeporodincnoDomacinstvo),
                        new PodaciOSamackomDomacinstvu(OSNOV_KORISTENJA_STANA.IZNAJMLJIVANJE_PRIVATNOG_STANA, null, samac)
                };

        Stream.of(popisaniPodaci).flatMap(PodaciODomacinstvu::getStreamOfClanovi).collect(Collectors.toList()).forEach(System.out::println);
    }
}
