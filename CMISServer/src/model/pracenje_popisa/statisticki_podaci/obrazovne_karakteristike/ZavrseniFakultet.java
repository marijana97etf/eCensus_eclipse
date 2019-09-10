package model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike;

public class ZavrseniFakultet extends ZavrsenaSkola
{
    private ProgramStudiranja programStudiranja;


    // CONSTRUCTOR

    public ZavrseniFakultet(Integer duzinaTrajanja, ProgramStudiranja programStudiranja) {
        super(duzinaTrajanja);
        this.programStudiranja = programStudiranja;
    }

    // GETTERS AND SETTERS


    public ProgramStudiranja getProgramStudiranja() {
        return programStudiranja;
    }

    public void setProgramStudiranja(ProgramStudiranja programStudiranja) {
        this.programStudiranja = programStudiranja;
    }
}
