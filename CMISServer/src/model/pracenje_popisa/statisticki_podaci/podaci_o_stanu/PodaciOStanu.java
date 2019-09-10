package model.pracenje_popisa.statisticki_podaci.podaci_o_stanu;

import java.time.Year;

public class PodaciOStanu {

    private NACIN_SNABDJEVANJA_VODOM_ZA_PICE nacinSnabdjevanjaVodomZaPice;
    private VRSTA_ENERGENTA vrstaEnergenta;
    private NACIN_ZAGRIJAVANJA nacinZagrijavanja;
    private Integer kvadraturaM2;
    private Boolean imaKupatilo;
    private POSJEDOVANJE_TOALETA posjedovanjeToaleta;
    private Year godinaIzgradnje;
    private BROJ_SOBA brojSoba;
    private SPRAT sprat;
    private NACIN_KORISTENJA_STANA nacinKoristenjaStana;
    private Zgrada zgrada;


    // CONSTRUCTOR

    public PodaciOStanu(Zgrada zgrada, NACIN_SNABDJEVANJA_VODOM_ZA_PICE nacinSnabdjevanjaVodomZaPice, VRSTA_ENERGENTA vrstaEnergenta, NACIN_ZAGRIJAVANJA nacinZagrijavanja, Integer kvadraturaM2, Boolean imaKupatilo, POSJEDOVANJE_TOALETA posjedovanjeToaleta, Year godinaIzgradnje, BROJ_SOBA brojSoba, SPRAT sprat, NACIN_KORISTENJA_STANA nacinKoristenjaStana) {
        this.nacinSnabdjevanjaVodomZaPice = nacinSnabdjevanjaVodomZaPice;
        this.vrstaEnergenta = vrstaEnergenta;
        this.nacinZagrijavanja = nacinZagrijavanja;
        this.kvadraturaM2 = kvadraturaM2;
        this.imaKupatilo = imaKupatilo;
        this.posjedovanjeToaleta = posjedovanjeToaleta;
        this.godinaIzgradnje = godinaIzgradnje;
        this.brojSoba = brojSoba;
        this.sprat = sprat;
        this.nacinKoristenjaStana = nacinKoristenjaStana;
        this.zgrada = zgrada;
    }


    // SETTERS AND GETTERS

    public NACIN_SNABDJEVANJA_VODOM_ZA_PICE getNacinSnabdjevanjaVodomZaPice() {
        return nacinSnabdjevanjaVodomZaPice;
    }

    public void setNacinSnabdjevanjaVodomZaPice(NACIN_SNABDJEVANJA_VODOM_ZA_PICE nacinSnabdjevanjaVodomZaPice) {
        this.nacinSnabdjevanjaVodomZaPice = nacinSnabdjevanjaVodomZaPice;
    }

    public VRSTA_ENERGENTA getVrstaEnergenta() {
        return vrstaEnergenta;
    }

    public void setVrstaEnergenta(VRSTA_ENERGENTA vrstaEnergenta) {
        this.vrstaEnergenta = vrstaEnergenta;
    }

    public NACIN_ZAGRIJAVANJA getNacinZagrijavanja() {
        return nacinZagrijavanja;
    }

    public void setNacinZagrijavanja(NACIN_ZAGRIJAVANJA nacinZagrijavanja) {
        this.nacinZagrijavanja = nacinZagrijavanja;
    }

    public Integer getKvadraturaM2() {
        return kvadraturaM2;
    }

    public void setKvadraturaM2(Integer kvadraturaM2) {
        this.kvadraturaM2 = kvadraturaM2;
    }

    public Boolean getImaKupatilo() {
        return imaKupatilo;
    }

    public void setImaKupatilo(Boolean imaKupatilo) {
        this.imaKupatilo = imaKupatilo;
    }

    public POSJEDOVANJE_TOALETA getPosjedovanjeToaleta() {
        return posjedovanjeToaleta;
    }

    public void setPosjedovanjeToaleta(POSJEDOVANJE_TOALETA posjedovanjeToaleta) {
        this.posjedovanjeToaleta = posjedovanjeToaleta;
    }

    public Year getGodinaIzgradnje() {
        return godinaIzgradnje;
    }

    public void setGodinaIzgradnje(Year godinaIzgradnje) {
        this.godinaIzgradnje = godinaIzgradnje;
    }

    public BROJ_SOBA getBrojSoba() {
        return brojSoba;
    }

    public void setBrojSoba(BROJ_SOBA brojSoba) {
        this.brojSoba = brojSoba;
    }

    public SPRAT getSprat() {
        return sprat;
    }

    public void setSprat(SPRAT sprat) {
        this.sprat = sprat;
    }

    public NACIN_KORISTENJA_STANA getNacinKoristenjaStana() {
        return nacinKoristenjaStana;
    }

    public void setNacinKoristenjaStana(NACIN_KORISTENJA_STANA nacinKoristenjaStana) {
        this.nacinKoristenjaStana = nacinKoristenjaStana;
    }

    public Zgrada getZgrada() {
        return zgrada;
    }

    public void setZgrada(Zgrada zgrada) {
        this.zgrada = zgrada;
    }
}
