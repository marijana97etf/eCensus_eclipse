package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class AdministratorAgencije extends Administrator {

	protected String nazivAgencije;
	
	public AdministratorAgencije() {}
	
    public AdministratorAgencije(String JMBG,
                                 String ime,
                                 String prezime,
                                 String korisnickoIme,
                                 String lozinka,
                                 JEZIK jezik,
                                 PISMO pismo,
                                 String nazivAgencije)
    {
        super(JMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo);
        this.nazivAgencije = nazivAgencije;
    }

	public AdministratorAgencije(long id, String jMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			JEZIK jezik, PISMO pismo, String nazivAgencije, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, jMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore, keyLozinka);
		this.nazivAgencije = nazivAgencije;
	}
	
	public String getNazivAgencije() {
		return nazivAgencije;
	}
}
