package model.korisnicki_nalozi;

public class ClanPKLS extends Administrator {
	
	protected String opstina;
	protected String grad;
	
	public ClanPKLS(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash, String grad,
					String opstina, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, trustStore, trustLozinka, keyStore, keyLozinka);
		 this.opstina = opstina;
	     this.grad = grad;
	}

	public ClanPKLS() {}
	
    public ClanPKLS(String ime,
                    String prezime,
                    String korisnickoIme,
                    String lozinkaHash,
                    String grad,
                    String opstina)
    {
        super(ime, prezime, korisnickoIme, lozinkaHash);
        this.opstina = opstina;
        this.grad = grad;
    }

	public String getOpstina() {
		return opstina;
	}

	public void setOpstina(String opstina) {
		this.opstina = opstina;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

}


