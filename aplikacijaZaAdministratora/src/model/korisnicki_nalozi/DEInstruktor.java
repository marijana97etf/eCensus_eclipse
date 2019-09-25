package model.korisnicki_nalozi;

public class DEInstruktor extends Administrator {

	protected ENTITET entitet;
	protected DRZAVA drzava;

	public ENTITET getEntitet() {
		return entitet;
	}

	public void setEntitet(ENTITET entitet) {
		this.entitet = entitet;
	}

	public DRZAVA getDrzava() {
		return drzava;
	}

	public void setDrzava(DRZAVA drzava) {
		this.drzava = drzava;
	}
	
	public DEInstruktor() {
	}

	public DEInstruktor(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
			DRZAVA drzava, ENTITET entitet, String trustStore, String trustLozinka,
			String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, trustStore, trustLozinka, keyStore,
				keyLozinka);
		this.entitet = entitet;
		this.drzava = drzava;
	}

	public DEInstruktor(String ime, String prezime, String korisnickoIme, String lozinkaHash,
			DRZAVA drzava, ENTITET entitet) {
		super(ime, prezime, korisnickoIme, lozinkaHash);
		this.entitet = entitet;
		this.drzava = drzava;
	}
	
	public enum DRZAVA {
		BIH("Bosna i Hercegovina");
		
		private String naziv;
		
		private DRZAVA(String naziv) {
			this.naziv = naziv;
		}
		
		public String toString() {
			return naziv;
		}
		
		public String getValue() {
	        return naziv;
	    }
	    
	    public static DRZAVA getDRZAVA(String naziv) {
	    	for(DRZAVA value : DRZAVA.values()) {
	    		if(value.getValue().equalsIgnoreCase(naziv)) {
	    			return value;
	    		}
	    	}
	    	return null;
	    }
	}
	
	public enum ENTITET {
		FBIH("Federacija Bosne i Hercegovine"),
		RS("Republika Srpska"),
		BRCKO_DISTRIKT("Brcko Distrikt");
		
		private String naziv;
		
		private ENTITET(String naziv) {
			this.naziv = naziv;
		}
		
		public String toString() {
			return naziv;
		}
		
		public String getValue() {
	        return naziv;
	    }
	    
	    public static ENTITET getENTITET(String naziv) {
	    	for(ENTITET value : ENTITET.values()) {
	    		if(value.getValue().equalsIgnoreCase(naziv)) {
	    			return value;
	    		}
	    	}
	    	return null;
	    }
	}
}
