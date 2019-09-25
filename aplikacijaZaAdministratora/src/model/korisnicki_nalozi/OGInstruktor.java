package model.korisnicki_nalozi;

public class OGInstruktor extends Administrator {
	
	protected String grad,opstina;
	
	public OGInstruktor() {}
	
    public OGInstruktor(String ime,
                        String prezime,
                        String korisnickoIme,
                        String lozinkaHash,
                        String grad,
                        String opstina)
    {
        super(ime, prezime, korisnickoIme, lozinkaHash);
        this.grad = grad;
        this.opstina = opstina;
    }

	public OGInstruktor(long id, String ime, String prezime, String korisnickoIme, String lozinkaHash,
						String grad, String opstina, String trustStore, String trustLozinka, String keyStore, String keyLozinka) {
		super(id, ime, prezime, korisnickoIme, lozinkaHash, trustStore, trustLozinka, keyStore, keyLozinka);
		this.grad = grad;
        this.opstina = opstina;
	}

	public void setGrad(String grad)
	{
		this.grad = grad;
	}
	
	public String getGrad() {
		return grad;
	}

	public String getOpstina() {
		return opstina;
	}

    public void setOpstina(String value) {
		opstina=value;
    }
}
