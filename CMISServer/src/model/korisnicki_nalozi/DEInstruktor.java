package model.korisnicki_nalozi;

import model.pracenje_popisa.JEZIK;
import model.pracenje_popisa.PISMO;

public class DEInstruktor extends Administrator {

	protected DRZAVA_ENTITET drzavaIliEntitet;

	public DRZAVA_ENTITET getDrzavaIliEntitet() {
		return drzavaIliEntitet;
	}

	public void setDrzavaIliEntitet(DRZAVA_ENTITET drzavaIliEntitet) {
		this.drzavaIliEntitet = drzavaIliEntitet;
	}

	public enum DRZAVA_ENTITET {
		BIH, FBIH, RS
	}

	public DEInstruktor() {
	}

	public DEInstruktor(long id, String jMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			DRZAVA_ENTITET drzavaIliEntitet, JEZIK jezik, PISMO pismo, String trustStore, String trustLozinka,
			String keyStore, String keyLozinka) {
		super(id, jMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo, trustStore, trustLozinka, keyStore,
				keyLozinka);
		this.drzavaIliEntitet = drzavaIliEntitet;
	}

	public DEInstruktor(String JMBG, String ime, String prezime, String korisnickoIme, String lozinka,
			DRZAVA_ENTITET drzavaIliEntitet, JEZIK jezik, PISMO pismo) {
		super(JMBG, ime, prezime, korisnickoIme, lozinka, jezik, pismo);
		this.drzavaIliEntitet = drzavaIliEntitet;
	}

	// Prints a line about Day using switch
	public String DrzavaEntitetToString() {
		switch (drzavaIliEntitet) {
		case BIH:
			return "Bosna i Hercegovina";
		case FBIH:
			return "Federacija Bosne i Hercegovine";
		case RS:
			return "Republika Srpska";
		default:
			return "NONE";
		}
	}

	public static DRZAVA_ENTITET StringTODrzavaEntitet(String name) {
		switch (name) {
		case "Bosna i Hercegovina":
			return DRZAVA_ENTITET.BIH;
		case "Federacija Bosne i Hercegovine":
			return DRZAVA_ENTITET.FBIH;
		case "Republika Srpska":
			return DRZAVA_ENTITET.RS;
		default:
			return null;
		}
	}
}
