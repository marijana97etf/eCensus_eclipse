package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class OGInstruktor extends Administrator {
	
	protected String grad,opstina;
	
	public OGInstruktor() {}
	
    public OGInstruktor(String JMBG,
                        String ime,
                        String prezime,
                        String korisnickoIme,
                        String lozinka,
                        JEZIK jezik,
                        PISMO pismo,
                        String grad,
                        String opstina)
    {
        super(JMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo);
        this.grad = grad;
        this.opstina = opstina;
    }

	public OGInstruktor(long id, String jMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			JEZIK jezik, PISMO pismo, String grad, String opstina, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, jMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore, keyLozinka);
		this.grad = grad;
        this.opstina = opstina;
	}

	public String getGrad() {
		return grad;
	}

	public String getOpstina() {
		return opstina;
	}
}
