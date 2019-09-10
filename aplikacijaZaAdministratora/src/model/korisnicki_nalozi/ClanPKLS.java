package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class ClanPKLS extends Administrator {
	
	public ClanPKLS(long id, String jMBG, String ime, String prezime, String korisnickoIme, String lozinka, JEZIK jezik,
			PISMO pismo, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, jMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore, keyLozinka);
		// TODO Auto-generated constructor stub
	}

	public ClanPKLS() {}
	
    public ClanPKLS(String JMBG,
                    String ime,
                    String prezime,
                    String korisnickoIme,
                    String lozinka,
                    JEZIK jezik,
                    PISMO pismo)
    {
        super(JMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo);
    }
}
