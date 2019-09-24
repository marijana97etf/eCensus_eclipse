package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class AdministratorAgencije extends Administrator {

	protected String nazivAgencije;
	
	public AdministratorAgencije() {}
	
    public AdministratorAgencije(String ime,
                                 String prezime,
                                 String korisnickoIme,
                                 String lozinkaHash,
                                 JEZIK jezik,
                                 PISMO pismo,
                                 String nazivAgencije)
    {
        super(ime, prezime, korisnickoIme, lozinkaHash, jezik, pismo);
        this.nazivAgencije = nazivAgencije;
    }

	public AdministratorAgencije(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
			JEZIK jezik, PISMO pismo, String nazivAgencije, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id,ime, prezime, korisnickoIme, lozinkaHash, jezik, pismo, trustStore, trustLozinka, keyStore, keyLozinka);
		this.nazivAgencije = nazivAgencije;
	}
	
	public String getNazivAgencije() {
		return nazivAgencije;
	}
}
