package model.korisnicki_nalozi;

public class PowerUser extends KorisnikSistema {

	public PowerUser() {
		// TODO Auto-generated constructor stub
	}

	public PowerUser(String ime, String prezime, String korisnickoIme, String lozinkaHash) {
		super(ime, prezime, korisnickoIme, lozinkaHash);
		// TODO Auto-generated constructor stub
	}

	public PowerUser(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
			String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, trustStore, trustLozinka, keyStore,
				keyLozinka);
		// TODO Auto-generated constructor stub
	}

}
