package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopisnicaZaDomacinstvo extends Popisnica implements Serializable{
    private int idZgrade;
    private String ulica;
    private String kucniBroj;
    private String dodatak;
    private String ulaz;
    private String brojStana;
    private String idBroj;
    private int brojClanovaDomacinstva;
    private int brojLicaUStanu;
    private int brojDomacinstavaUStanu;
    private List<ClanDomacinstva> spisakLica;

    public PopisnicaZaDomacinstvo(){
    }

    public PopisnicaZaDomacinstvo(int idObrasca, int idEntiteta, int idOpstine, int idPopisnogKruga, int idStana, int idDomacinstva,
                                  int idZgrade, String ulica, String kucniBroj, String dodatak, String ulaz, String brojStana, String idBroj) {
        super(idObrasca, idEntiteta, idOpstine, idPopisnogKruga, idStana, idDomacinstva);
        this.idZgrade = idZgrade;
        this.ulica = ulica;
        this.kucniBroj = kucniBroj;
        this.dodatak = dodatak;
        this.ulaz = ulaz;
        this.brojStana = brojStana;
        this.idBroj = idBroj;
        odgovoriNaPitanja = new HashMap<>();
    }

    public int getIdZgrade() {
        return idZgrade;
    }

    public void setIdZgrade(int idZgrade) {
        this.idZgrade = idZgrade;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getKucniBroj() {
        return kucniBroj;
    }

    public void setKucniBroj(String kucniBroj) {
        this.kucniBroj = kucniBroj;
    }

    public String getDodatak() {
        return dodatak;
    }

    public void setDodatak(String dodatak) {
        this.dodatak = dodatak;
    }

    public String getUlaz() {
        return ulaz;
    }

    public void setUlaz(String ulaz) {
        this.ulaz = ulaz;
    }

    public String getBrojStana() {
        return brojStana;
    }

    public void setBrojStana(String brojStana) {
        this.brojStana = brojStana;
    }

    public String getIdBroj() {
        return idBroj;
    }

    public void setIdBroj(String idBroj) {
        this.idBroj = idBroj;
    }

    public int getBrojClanovaDomacinstva() {
        return brojClanovaDomacinstva;
    }

    public void setBrojClanovaDomacinstva(int brojClanovaDomacinstva) {
        this.brojClanovaDomacinstva = brojClanovaDomacinstva;
    }

    public int getBrojLicaUStanu() {
        return brojLicaUStanu;
    }

    public void setBrojLicaUStanu(int brojLicaUStanu) {
        this.brojLicaUStanu = brojLicaUStanu;
    }

    public int getBrojDomacinstavaUStanu() {
        return brojDomacinstavaUStanu;
    }

    public void setBrojDomacinstavaUStanu(int brojDomacinstavaUStanu) {
        this.brojDomacinstavaUStanu = brojDomacinstavaUStanu;
    }
    
    public List<ClanDomacinstva> getSpisakLica() {
		return spisakLica;
	}

	public void setSpisakLica(List<ClanDomacinstva> spisakLica) {
		this.spisakLica = spisakLica;
	}
}
