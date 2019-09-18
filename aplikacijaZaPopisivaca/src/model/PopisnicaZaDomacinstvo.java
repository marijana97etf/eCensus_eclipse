package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopisnicaZaDomacinstvo {
    private int idObrasca;
    private int idEntiteta;
    private int idOpstine;
    private int idPopisnogKruga;
    private int idStana;
    private int idDomacinstva;
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

    private Map<Integer, List<String>> odgovoriNaPitanja;

    public PopisnicaZaDomacinstvo(){
        odgovoriNaPitanja = new HashMap<>();
    }

    public PopisnicaZaDomacinstvo(int idObrasca, int idEntiteta, int idOpstine, int idPopisnogKruga, int idStana, int idDomacinstva,
                                  int idZgrade, String ulica, String kucniBroj, String dodatak, String ulaz, String brojStana, String idBroj) {
        this.idObrasca = idObrasca;
        this.idEntiteta = idEntiteta;
        this.idOpstine = idOpstine;
        this.idPopisnogKruga = idPopisnogKruga;
        this.idStana = idStana;
        this.idDomacinstva = idDomacinstva;
        this.idZgrade = idZgrade;
        this.ulica = ulica;
        this.kucniBroj = kucniBroj;
        this.dodatak = dodatak;
        this.ulaz = ulaz;
        this.brojStana = brojStana;
        this.idBroj = idBroj;
        odgovoriNaPitanja = new HashMap<>();
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

	public Map<Integer, List<String>> getOdgovoriNaPitanja() {
        return odgovoriNaPitanja;
    }

    public void setOdgovoriNaPitanja(Map<Integer, List<String>> odgovoriNaPitanja) {
        this.odgovoriNaPitanja = odgovoriNaPitanja;
    }
}
