package model.korisnicki_nalozi;

import java.lang.reflect.Constructor;

import jdk.jshell.spi.ExecutionControl;
import model.pracenje_popisa.Izvjestaj;

public abstract class Administrator extends KorisnikSistema
{
	public Administrator() {}
	
    public Administrator(String ime,
                         String prezime,
                         String korisnickoIme,
                         String lozinkaHash)
    {
        super(ime, prezime, korisnickoIme, lozinkaHash);
    }

    public Administrator(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, trustStore, trustLozinka, keyStore, keyLozinka);
	}
}