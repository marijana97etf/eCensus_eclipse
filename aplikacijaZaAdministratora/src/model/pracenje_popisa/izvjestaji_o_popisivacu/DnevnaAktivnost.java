package model.pracenje_popisa.izvjestaji_o_popisivacu;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class DnevnaAktivnost extends Aktivnost {

    protected LocalDate dan;
    protected int brojPopisanihStanovnika;
    protected int brojPopisanihDomacinstava;

    public DnevnaAktivnost() {
    	
    }
    
    public DnevnaAktivnost(LocalDate dan, int brojPopisanihStanovnika, int brojPopisanihDomacinstava) {
        this.brojPopisanihStanovnika = brojPopisanihStanovnika;
    	this.brojPopisanihDomacinstava = brojPopisanihDomacinstava;
        this.dan = dan;
    }

    public LocalDate getDan() {
        return dan;
    }

    public void setDan(LocalDate dan) {
        this.dan = dan;
    }

    public int getBrojPopisanihStanovnika() {
        return brojPopisanihStanovnika;
    }

    public void setBrojPopisanihStanovnika(int brojPopisanihStanovnika) {
        this.brojPopisanihStanovnika = brojPopisanihStanovnika;
    }
    
    public int getBrojPopisanihDomacinstava() {
        return brojPopisanihDomacinstava;
    }

    public void setBrojPopisanihDomacinstava(int brojPopisanihDomacinstava) {
        this.brojPopisanihDomacinstava = brojPopisanihDomacinstava;
    }

}
