package model.korisnicki_nalozi;

import java.util.LinkedList;
import java.util.List;

import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class Popisivac extends KorisnikSistema {

	protected List<PopisniKrug> dodijeljeniPopisniKrugovi = new LinkedList<>();
	
	public Popisivac() {}
	
	public Popisivac(String ime, String prezime, String korisnickoIme, String lozinkaHash) {
		super(ime, prezime, korisnickoIme, lozinkaHash);
	}

	public Popisivac(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
					 String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, trustStore, trustLozinka, keyStore,
				keyLozinka);
	}
	
	public void dodajPopisniKrug(PopisniKrug popisniKrug) {
		dodijeljeniPopisniKrugovi.add(popisniKrug);
	}
	
	public void obrisiPopisniKrug(PopisniKrug popisniKrug) {
		dodijeljeniPopisniKrugovi.remove(popisniKrug);
	}

}
