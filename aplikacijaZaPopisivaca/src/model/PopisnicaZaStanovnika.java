package model;

import java.io.Serializable;

public class PopisnicaZaStanovnika extends Popisnica implements Serializable{
    private int idLica;
    private String ime;
    private String imeOcaIliMajke;
    private String prezime;
    private String JMBG;
    private String pol;

    public PopisnicaZaStanovnika() {
    }

    public PopisnicaZaStanovnika(int idObrasca, int idEntiteta, int idOpstine, int idPopisnogKruga, int idStana, int idDomacinstva,
                                 int idLica, String ime, String imeOcaIliMajke, String prezime, String JMBG, String pol)
    {
    	super(idObrasca, idEntiteta, idOpstine, idPopisnogKruga, idStana, idDomacinstva);
        this.idLica = idLica;
        this.ime = ime;
        this.imeOcaIliMajke = imeOcaIliMajke;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.pol = pol;
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
}
