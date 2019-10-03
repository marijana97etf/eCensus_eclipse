package model.pracenje_popisa.izvjestaji_o_popisivacu;

public class Kontrolnik {

	protected int idPopisnogKruga;
	protected int idOpstine;

	protected int brojPopisanihStanova;
	protected int brojPopisanihDomacinstava;
	protected int brojClanovaDomacinstava;
	
	public Kontrolnik() {
		super();
	}
	
	public Kontrolnik(int idPopisnogKruga, int idOpstine, int brojPopisanihStanova, int brojPopisanihDomacinstava,
			int brojClanovaDomacinstava) {
		super();
		this.idPopisnogKruga = idPopisnogKruga;
		this.idOpstine = idOpstine;
		this.brojPopisanihStanova = brojPopisanihStanova;
		this.brojPopisanihDomacinstava = brojPopisanihDomacinstava;
		this.brojClanovaDomacinstava = brojClanovaDomacinstava;
	}

	public int getIdPopisnogKruga() {
		return idPopisnogKruga;
	}

	public void setIdPopisnogKruga(int idPopisnogKruga) {
		this.idPopisnogKruga = idPopisnogKruga;
	}

	public int getIdOpstine() {
		return idOpstine;
	}

	public void setIdOpstine(int idOpstine) {
		this.idOpstine = idOpstine;
	}

	public int getBrojPopisanihStanova() {
		return brojPopisanihStanova;
	}

	public void setBrojPopisanihStanova(int brojPopisanihStanova) {
		this.brojPopisanihStanova = brojPopisanihStanova;
	}

	public int getBrojPopisanihDomacinstava() {
		return brojPopisanihDomacinstava;
	}

	public void setBrojPopisanihDomacinstava(int brojPopisanihDomacinstava) {
		this.brojPopisanihDomacinstava = brojPopisanihDomacinstava;
	}

	public int getBrojClanovaDomacinstava() {
		return brojClanovaDomacinstava;
	}

	public void setBrojClanovaDomacinstava(int brojClanovaDomacinstava) {
		this.brojClanovaDomacinstava = brojClanovaDomacinstava;
	}
	
}