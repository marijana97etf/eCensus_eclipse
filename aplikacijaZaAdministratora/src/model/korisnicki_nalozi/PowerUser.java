package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class PowerUser extends KorisnikSistema {

	public PowerUser() {
		// TODO Auto-generated constructor stub
	}

	public PowerUser(String ime, String prezime, String korisnickoIme, String lozinkaHash, JEZIK jezik,
			PISMO pismo) {
		super(ime, prezime, korisnickoIme, lozinkaHash, jezik, pismo);
		// TODO Auto-generated constructor stub
	}

	public PowerUser(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
			JEZIK jezik, PISMO pismo, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, jezik, pismo, trustStore, trustLozinka, keyStore,
				keyLozinka);
		// TODO Auto-generated constructor stub
	}

}
