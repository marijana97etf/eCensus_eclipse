package model.korisnicki_nalozi;

import java.util.LinkedList;
import java.util.List;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class Popisivac extends KorisnikSistema {

	protected List<PopisniKrug> dodijeljeniPopisniKrugovi = new LinkedList<>();
	
	public Popisivac() {}
	
	public Popisivac(String ime, String prezime, String korisnickoIme, String lozinka, JEZIK jezik,
			PISMO pismo) {
		super(ime, prezime, korisnickoIme, lozinka, jezik, pismo);
	}

	public Popisivac(long id, String ime, String prezime, String korisnickoIme, String lozinka,
			JEZIK jezik, PISMO pismo, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore,
				keyLozinka);
		// TODO Auto-generated constructor stub
	}
	
	public void dodajPopisniKrug(PopisniKrug popisniKrug) {
		dodijeljeniPopisniKrugovi.add(popisniKrug);
	}
	
	public void obrisiPopisniKrug(PopisniKrug popisniKrug) {
		dodijeljeniPopisniKrugovi.remove(popisniKrug);
	}

}
