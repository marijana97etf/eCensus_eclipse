package model.pracenje_popisa.statisticki_podaci.podaci_o_stanu;

import java.time.Year;

public class Zgrada {
    private SPRAT brojSpratova;
    private Boolean imaLift;
    private PRISTUPNOST_ZGRADI pristupnostZgradi;
    private Year godinaIzgradnje;
    private GRADJA_KROVA gradjaKrova;
    private TIP_ZGRADE tip;
    private MATERIJAL_NOSIVOG_SISTEMA tipMaterijalaNosivogSistema;
    private Integer brojStanova;

    // CONSTRUCTOR


    public Zgrada(SPRAT brojSpratova,
                  Boolean imaLift,
                  PRISTUPNOST_ZGRADI pristupnostZgradi,
                  Year godinaIzgradnje, GRADJA_KROVA gradjaKrova,
                  TIP_ZGRADE tip, MATERIJAL_NOSIVOG_SISTEMA tipMaterijalaNosivogSistema,
                  Integer brojStanova)
    {
        this.brojSpratova = brojSpratova;
        this.imaLift = imaLift;
        this.pristupnostZgradi = pristupnostZgradi;
        this.godinaIzgradnje = godinaIzgradnje;
        this.gradjaKrova = gradjaKrova;
        this.tip = tip;
        this.tipMaterijalaNosivogSistema = tipMaterijalaNosivogSistema;
        this.brojStanova = brojStanova;
    }
}
