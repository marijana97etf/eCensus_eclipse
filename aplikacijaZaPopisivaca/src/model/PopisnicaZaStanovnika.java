package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopisnicaZaStanovnika {
    private int idObrasca;
    private int idEntiteta;
    private int idOpstine;
    private int idPopisnogKruga;
    private int idStana;
    private int idDomacinstva;
    private int idLica;
    private String ime;
    private String imeOcaIliMajke;
    private String prezime;
    private String JMBG;
    private String pol;

    private Map<Integer, List<String>> odgovoriNaPitanja;

    public PopisnicaZaStanovnika() {
        odgovoriNaPitanja = new HashMap<>();
    }

    public PopisnicaZaStanovnika(int idObrasca, int idEntiteta, int idOpstine, int idPopisnogKruga, int idStana, int idDomacinstva,
                                 int idLica, String ime, String imeOcaIliMajke, String prezime, String JMBG, String pol)
    {
        this.idObrasca = idObrasca;
        this.idEntiteta = idEntiteta;
        this.idOpstine = idOpstine;
        this.idPopisnogKruga = idPopisnogKruga;
        this.idStana = idStana;
        this.idDomacinstva = idDomacinstva;
        this.idLica = idLica;
        this.ime = ime;
        this.imeOcaIliMajke = imeOcaIliMajke;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.pol = pol;
    }

    public int getIdObrasca() {
        return idObrasca;
    }

    public void setIdObrasca(int idObrasca) {
        this.idObrasca = idObrasca;
    }

    public int getIdEntiteta() {
        return idEntiteta;
    }

    public void setIdEntiteta(int idEntiteta) {
        this.idEntiteta = idEntiteta;
    }

    public int getIdOpstine() {
        return idOpstine;
    }

    public void setIdOpstine(int idOpstine) {
        this.idOpstine = idOpstine;
    }

    public int getIdPopisnogKruga() {
        return idPopisnogKruga;
    }

    public void setIdPopisnogKruga(int idPopisnogKruga) {
        this.idPopisnogKruga = idPopisnogKruga;
    }

    public int getIdStana() {
        return idStana;
    }

    public void setIdStana(int idStana) {
        this.idStana = idStana;
    }

    public int getIdDomacinstva() {
        return idDomacinstva;
    }

    public void setIdDomacinstva(int idDomacinstva) {
        this.idDomacinstva = idDomacinstva;
    }

    public int getIdLica() {
        return idLica;
    }

    public void setIdLica(int idLica) {
        this.idLica = idLica;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getImeOcaIliMajke() {
        return imeOcaIliMajke;
    }

    public void setImeOcaIliMajke(String imeOcaIliMajke) {
        this.imeOcaIliMajke = imeOcaIliMajke;
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

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public Map<Integer, List<String>> getOdgovoriNaPitanja() {
        return odgovoriNaPitanja;
    }

    public void setOdgovoriNaPitanja(Map<Integer, List<String>> odgovoriNaPitanja) {
        this.odgovoriNaPitanja = odgovoriNaPitanja;
    }
}
