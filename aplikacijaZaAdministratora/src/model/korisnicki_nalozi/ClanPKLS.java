package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class ClanPKLS extends Administrator {
	
	protected String opstina,grad;
	
	public ClanPKLS(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash, JEZIK jezik,
			PISMO pismo, String grad, String opstina, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, jezik, pismo, trustStore, trustLozinka, keyStore, keyLozinka);
		 this.opstina = opstina;
	     this.grad = grad;
	}

	public ClanPKLS() {}
	
    public ClanPKLS(String ime,
                    String prezime,
                    String korisnickoIme,
                    String lozinkaHash,
                    JEZIK jezik,
                    PISMO pismo,
                    String grad,
                    String opstina)
    {
        super(ime, prezime, korisnickoIme, lozinkaHash, jezik, pismo);
        this.opstina = opstina;
        this.grad = grad;
    }
}


