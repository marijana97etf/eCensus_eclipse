package model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike;

public class StariProgram implements ProgramStudiranja {
    private TIP_STUDIJE_PO_STAROM_PROGRAMU tipStudijePoStaromProgramu;


    // CONSTRUCTOR

    public StariProgram(TIP_STUDIJE_PO_STAROM_PROGRAMU tipStudijePoStaromProgramu) {
        this.tipStudijePoStaromProgramu = tipStudijePoStaromProgramu;
    }


    // SETTERS AND GETTERS

    public TIP_STUDIJE_PO_STAROM_PROGRAMU getTipStudijePoStaromProgramu() {
        return tipStudijePoStaromProgramu;
    }

    public void setTipStudijePoStaromProgramu(TIP_STUDIJE_PO_STAROM_PROGRAMU tipStudijePoStaromProgramu) {
        this.tipStudijePoStaromProgramu = tipStudijePoStaromProgramu;
    }
}
