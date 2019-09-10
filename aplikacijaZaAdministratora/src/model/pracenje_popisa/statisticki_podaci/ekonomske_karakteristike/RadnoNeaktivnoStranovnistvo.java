package model.pracenje_popisa.statisticki_podaci.ekonomske_karakteristike;

public class RadnoNeaktivnoStranovnistvo {

    private EKONOMSKI_NEAKTIVNI tipNeaktivnosti;


    // CONSTRUCTOR

    public RadnoNeaktivnoStranovnistvo(EKONOMSKI_NEAKTIVNI tipNeaktivnosti) {
        this.tipNeaktivnosti = tipNeaktivnosti;
    }


    // SETTERS AND GETTERS

    public EKONOMSKI_NEAKTIVNI getTipNeaktivnosti() {
        return tipNeaktivnosti;
    }

    public void setTipNeaktivnosti(EKONOMSKI_NEAKTIVNI tipNeaktivnosti) {
        this.tipNeaktivnosti = tipNeaktivnosti;
    }
}
