package model.korisnicki_nalozi;

import java.lang.reflect.Constructor;

import jdk.jshell.spi.ExecutionControl;
import model.pracenje_popisa.Izvjestaj;
import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public abstract class Administrator extends KorisnikSistema
{
	public Administrator() {}
	
    public Administrator(String JMBG,
                         String ime,
                         String prezime,
                         String korisnickoIme,
                         String lozinka,
                         JEZIK jezik,
                         PISMO pismo)
    {
        super(JMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo);
    }

    public Administrator(long id, String jMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			JEZIK jezik, PISMO pismo, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, jMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore, keyLozinka);
		// TODO Auto-generated constructor stub
	}

	public Izvjestaj kreirajIzvjestaj(Class<? extends Izvjestaj> izvjestaj, Object... params) throws Exception {
        Constructor constructors[] = izvjestaj.getDeclaredConstructors();
        Object obj = null;
        for (Constructor cons : constructors) {
            Class[] paramsOfConstructor = cons.getParameterTypes();
            if (paramsOfConstructor.length == 1 && paramsOfConstructor[0] == String.class) {
                obj = cons.newInstance(params);
                break;
            }
            else if(paramsOfConstructor.length == 0){
                obj = cons.newInstance(new Object(){});
                }
            else
                throw new ExecutionControl.NotImplementedException("TODO");
        }
        return (Izvjestaj) obj;
    }
}