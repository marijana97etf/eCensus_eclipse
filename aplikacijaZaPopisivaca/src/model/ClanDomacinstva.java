package model;

import java.io.Serializable;

public class ClanDomacinstva implements Serializable{
    private String ime;
    private String prezime;
    private String JMBG;
    private String odnosPremaNosiocuDomacinstva;
    private int redniBrojPorodice;
    private String polozajClanaUPorodici;

    public ClanDomacinstva() {
    }

    public ClanDomacinstva(String ime, String prezime, String JMBG, String odnosPremaNosiocuDomacinstva,
                           int redniBrojPorodice, String polozajClanaUPorodici) {
        this.ime = ime;
        this.prezime = prezime;
        this.JMBG = JMBG;
        this.odnosPremaNosiocuDomacinstva = odnosPremaNosiocuDomacinstva;
        this.redniBrojPorodice = redniBrojPorodice;
        this.polozajClanaUPorodici = polozajClanaUPorodici;
    }

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

    public String getOdnosPremaNosiocuDomacinstva() {
        return odnosPremaNosiocuDomacinstva;
    }

    public void setOdnosPremaNosiocuDomacinstva(String odnosPremaNosiocuDomacinstva) {
        this.odnosPremaNosiocuDomacinstva = odnosPremaNosiocuDomacinstva;
    }

    public int getRedniBrojPorodice() {
        return redniBrojPorodice;
    }

    public void setRedniBrojPorodice(int redniBrojPorodice) {
        this.redniBrojPorodice = redniBrojPorodice;
    }

    public String getPolozajClanaUPorodici() {
        return polozajClanaUPorodici;
    }

    public void setPolozajClanaUPorodici(String polozajClanaUPorodici) {
        this.polozajClanaUPorodici = polozajClanaUPorodici;
    }
}
