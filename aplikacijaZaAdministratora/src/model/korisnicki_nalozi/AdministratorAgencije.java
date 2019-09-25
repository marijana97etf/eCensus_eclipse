package model.korisnicki_nalozi;

public class AdministratorAgencije extends Administrator {

	protected String nazivAgencije;
	
	public AdministratorAgencije() {}
	
    public AdministratorAgencije(String ime,
                                 String prezime,
                                 String korisnickoIme,
                                 String lozinkaHash,
                                 String nazivAgencije)
    {
        super(ime, prezime, korisnickoIme, lozinkaHash);
        this.nazivAgencije = nazivAgencije;
    }

	public AdministratorAgencije(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
								 String nazivAgencije, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id,ime, prezime, korisnickoIme, lozinkaHash, trustStore, trustLozinka, keyStore, keyLozinka);
		this.nazivAgencije = nazivAgencije;
	}
	
	public String getNazivAgencije() {
		return nazivAgencije;
	}
}
