package model.pracenje_popisa.statisticki_podaci;

import model.pracenje_popisa.statisticki_podaci.bracni_status_fertilitet_zena.BracniStatusIFertilitetZena;
import model.pracenje_popisa.statisticki_podaci.ekonomske_karakteristike.EkonomskeKarakteristike;
import model.pracenje_popisa.statisticki_podaci.obiljezja_nacije.NacionalnaObiljezja;
import model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike.ObrazovneKarakteristike;
import model.pracenje_popisa.statisticki_podaci.starost_pol.StarostIPol;

public class PodaciOStanovniku implements PopisaniPodaci {

    protected String ime;
    protected String prezime;
    protected String JMBG;

    protected StarostIPol starostIPol;
    protected BracniStatusIFertilitetZena bracniStatusIFertilitetZenskogStanovnistva;
    protected NacionalnaObiljezja nacionalnaObiljezja;
    protected EkonomskeKarakteristike ekonomskeKaratkeristike;
    protected ObrazovneKarakteristike obrazovneKarakteristike;


    // CONSTRUCTOR

    public PodaciOStanovniku(String ime,
                             String prezime,
                             String JMBG,
                             StarostIPol starostIPol,
                             BracniStatusIFertilitetZena bracniStatusIFertilitetZenskogStanovnistva,
                             NacionalnaObiljezja nacionalnaObiljezja,
                             EkonomskeKarakteristike ekonomskeKaratkeristike,
                             ObrazovneKarakteristike obrazovneKarakteristike) {
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.starostIPol = starostIPol;
        this.bracniStatusIFertilitetZenskogStanovnistva = bracniStatusIFertilitetZenskogStanovnistva;
        this.nacionalnaObiljezja = nacionalnaObiljezja;
        this.ekonomskeKaratkeristike = ekonomskeKaratkeristike;
        this.obrazovneKarakteristike = obrazovneKarakteristike;
    }

    public PodaciOStanovniku(String ime, String prezime, String JMBG)
    {
        this.ime=ime;
        this.prezime=prezime;
        this.JMBG = JMBG;
    }


    // SETTERS AND GETTERS

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public StarostIPol getStarostIPol() {
        return starostIPol;
    }

    public void setStarostIPol(StarostIPol starostIPol) {
        this.starostIPol = starostIPol;
    }

    public BracniStatusIFertilitetZena getBracniStatusIFertilitetZenskogStanovnistva() {
        return bracniStatusIFertilitetZenskogStanovnistva;
    }

    public void setBracniStatusIFertilitetZenskogStanovnistva(BracniStatusIFertilitetZena bracniStatusIFertilitetZenskogStanovnistva) {
        this.bracniStatusIFertilitetZenskogStanovnistva = bracniStatusIFertilitetZenskogStanovnistva;
    }

    public NacionalnaObiljezja getNacionalnaObiljezja() {
        return nacionalnaObiljezja;
    }

    public void setNacionalnaObiljezja(NacionalnaObiljezja nacionalnaObiljezja) {
        this.nacionalnaObiljezja = nacionalnaObiljezja;
    }

    public EkonomskeKarakteristike getEkonomskeKaratkeristike() {
        return ekonomskeKaratkeristike;
    }

    public void setEkonomskeKaratkeristike(EkonomskeKarakteristike ekonomskeKaratkeristike) {
        this.ekonomskeKaratkeristike = ekonomskeKaratkeristike;
    }

    public ObrazovneKarakteristike getObrazovneKarakteristike() {
        return obrazovneKarakteristike;
    }

    public void setObrazovneKarakteristike(ObrazovneKarakteristike obrazovneKarakteristike) {
        this.obrazovneKarakteristike = obrazovneKarakteristike;
    }

    // METHODS


    @Override
    public String toString() {
        String newLine = System.lineSeparator();
        return "PodaciOStanovniku" + newLine + "{" + newLine +
                "   ime='" + ime + '\'' + newLine +
                "   prezime='" + prezime + '\'' + newLine +
                "   JMBG='" + JMBG + '\'' + newLine +
                "   starostIPol=" + starostIPol + newLine +
                "   bracniStatusIFertilitetZenskogStanovnistva=" + bracniStatusIFertilitetZenskogStanovnistva + newLine +
                "   nacionalnaObiljezja=" + nacionalnaObiljezja + newLine +
                "   ekonomskeKaratkeristike=" + ekonomskeKaratkeristike + newLine +
                "   obrazovneKarakteristike=" + obrazovneKarakteristike + newLine +
                '}';
    }
}
