package model.pracenje_popisa.statisticki_podaci.obrazovne_karakteristike;

public abstract class ZavrsenaSkola {
    private Integer duzinaTrajanja;


    // CONSTRUCTOR

    public ZavrsenaSkola(Integer duzinaTrajanja) {
        this.duzinaTrajanja = duzinaTrajanja;
    }


    // SETTERS AND GETTERS

    public Integer getDuzinaTrajanja() {
        return duzinaTrajanja;
    }

    public void setDuzinaTrajanja(Integer duzinaTrajanja) {
        this.duzinaTrajanja = duzinaTrajanja;
    }
}
