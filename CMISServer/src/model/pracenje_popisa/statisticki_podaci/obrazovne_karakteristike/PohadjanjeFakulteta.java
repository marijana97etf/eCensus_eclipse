package model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike;

public class PohadjanjeFakulteta implements PohadjanjeSkole {

    private ProgramStudiranja programStudiranja;

    // CONSTRUCTOR

    public PohadjanjeFakulteta(ProgramStudiranja programStudiranja) {
        this.programStudiranja = programStudiranja;
    }

    // SETTERS AND GETTERS


    public ProgramStudiranja getProgramStudiranja() {
        return programStudiranja;
    }

    public void setProgramStudiranja(ProgramStudiranja programStudiranja) {
        this.programStudiranja = programStudiranja;
    }
}
