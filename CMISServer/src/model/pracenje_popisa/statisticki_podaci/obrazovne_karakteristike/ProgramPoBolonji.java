package model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike;

public class ProgramPoBolonji implements ProgramStudiranja
{
    private TIP_STUDIJE_PO_PROGRAMU_PO_BOLONJI tipStudijePoProgramuPoBolonji;


    // CONSTRUCTOR

    public ProgramPoBolonji(TIP_STUDIJE_PO_PROGRAMU_PO_BOLONJI tipStudijePoProgramuPoBolonji) {
        this.tipStudijePoProgramuPoBolonji = tipStudijePoProgramuPoBolonji;
    }


    // SETTERS AND GETTERS

    public TIP_STUDIJE_PO_PROGRAMU_PO_BOLONJI getTipStudijePoProgramuPoBolonji() {
        return tipStudijePoProgramuPoBolonji;
    }

    public void setTipStudijePoProgramuPoBolonji(TIP_STUDIJE_PO_PROGRAMU_PO_BOLONJI tipStudijePoProgramuPoBolonji) {
        this.tipStudijePoProgramuPoBolonji = tipStudijePoProgramuPoBolonji;
    }
}
