package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class DEInstruktor extends Administrator {

	protected ENTITET entitet;
	protected String drzava = "BIH";

	public ENTITET getEntitet() {
		return entitet;
	}

	public void setEntitet(ENTITET drzavaIliEntitet) {
		this.entitet = drzavaIliEntitet;
	}

	public enum ENTITET {
		BRCKO_DISTRIKT, FBIH, RS
	}

	public DEInstruktor() {
	}

	public DEInstruktor(long id, String jMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			ENTITET entitet, JEZIK jezik, PISMO pismo, String trustStore, String trustLozinka,
			String keyStore, String keyLozinka) {
		super(id, jMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore,
				keyLozinka);
		this.entitet = entitet;
	}

	public DEInstruktor(String JMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			ENTITET entitet, JEZIK jezik, PISMO pismo) {
		super(JMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo);
		this.entitet = entitet;
	}

	// Prints a line about Day using switch
	public String entitetToString() {
		switch (entitet) {
		case BRCKO_DISTRIKT:
			return "Brcko Distrikt";
		case FBIH:
			return "Federacija Bosne i Hercegovine";
		case RS:
			return "Republika Srpska";
		default:
			return "NONE";
		}
	}

	public static ENTITET stringToEntitet(String name) {
		switch (name) {
		case "Federacija Bosne i Hercegovine":
			return ENTITET.FBIH;
		case "Republika Srpska":
			return ENTITET.RS;
		default:
			return null;
		}
	}
}
