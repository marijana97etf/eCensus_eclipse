package model.korisnicki_nalozi;

import java.util.LinkedList;
import java.util.List;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;
import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public class Popisivac extends KorisnikSistema {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<PopisniKrug> dodijeljeniPopisniKrugovi = new LinkedList<>();
	
	public Popisivac() {}
	
	public Popisivac(String JMBG, String ime, String prezime, String korisnickoIme, String lozinka, JEZIK jezik,
			PISMO pismo) {
		super(JMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo);
		// TODO Auto-generated constructor stub
	}

	public Popisivac(long id, String jMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			JEZIK jezik, PISMO pismo, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, jMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore,
				keyLozinka);
		// TODO Auto-generated constructor stub
	}
	
	public void dodajPopisneKrugove(List<PopisniKrug> popisniKrugovi) {
		dodijeljeniPopisniKrugovi.addAll(popisniKrugovi);
	}
	
	public void dodajPopisniKrug(PopisniKrug popisniKrug) {
		dodijeljeniPopisniKrugovi.add(popisniKrug);
	}
	
	public void obrisiPopisniKrug(PopisniKrug popisniKrug) {
		dodijeljeniPopisniKrugovi.remove(popisniKrug);
	}
	
	public List<PopisniKrug> getdodijeljeniPopisniKrugovi(){
		return dodijeljeniPopisniKrugovi;
	}

	public void setDodijeljeniPopisniKrugovi(List<PopisniKrug> dodijeljeniPopisniKrugovi) {
		this.dodijeljeniPopisniKrugovi = dodijeljeniPopisniKrugovi;
	}

}
