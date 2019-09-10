package model.pracenje_popisa.statisticki_podaci.starost_pol;

public class StarostIPol {
    private Integer starost;
    /**
     * pol
     */
    private POL pol;

    // CONSTRUCTOR

    public StarostIPol(Integer starost, POL pol) {
        this.starost = starost;
        this.pol = pol;
    }

    // SETTERS AND GETTERS


    public Integer getStarost() {
        return starost;
    }

    public void setStarost(Integer starost) {
        this.starost = starost;
    }

    public POL getPol() {
        return pol;
    }

    public void setPol(POL pol) {
        this.pol = pol;
    }

    @Override
    public String toString() {
        return "StarostIPol{" +
                "starost=" + starost +
                ", pol=" + pol +
                '}';
    }
}
