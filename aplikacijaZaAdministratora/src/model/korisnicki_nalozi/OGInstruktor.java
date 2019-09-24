package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class OGInstruktor extends Administrator {
	
	protected String grad,opstina;
	
	public OGInstruktor() {}
	
    public OGInstruktor(String ime,
                        String prezime,
                        String korisnickoIme,
                        String lozinka,
                        JEZIK jezik,
                        PISMO pismo,
                        String grad,
                        String opstina)
    {
        super(ime, prezime, korisnickoIme, lozinka, jezik, pismo);
        this.grad = grad;
        this.opstina = opstina;
    }

	public OGInstruktor(long id, String ime, String prezime, String korisnickoIme, String lozinka,
			JEZIK jezik, PISMO pismo, String grad, String opstina, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore, keyLozinka);
		this.grad = grad;
        this.opstina = opstina;
	}

	public void setGrad(String grad)
	{
		this.grad = grad;
		this.opstina = grad;
	}
	
	public String getGrad() {
		return grad;
	}

	public String getOpstina() {
		return opstina;
	}
}
