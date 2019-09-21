package model;

public class ElementTabeleDomacinstvo {
	int obrazac;
	int entitet;
	int opstina;
	int popisniKrug;
	int zgrada;
	int stan;
	int domacinstvo;
	
	public ElementTabeleDomacinstvo(int obrazac, int entitet, int opstina, int popisniKrug, int zgrada, int stan,
			int domacinstvo) {
		super();
		this.obrazac = obrazac;
		this.entitet = entitet;
		this.opstina = opstina;
		this.popisniKrug = popisniKrug;
		this.zgrada = zgrada;
		this.stan = stan;
		this.domacinstvo = domacinstvo;
	}

	public int getObrazac() {
		return obrazac;
	}

	public void setObrazac(int obrazac) {
		this.obrazac = obrazac;
	}

	public int getEntitet() {
		return entitet;
	}

	public void setEntitet(int entitet) {
		this.entitet = entitet;
	}

	public int getOpstina() {
		return opstina;
	}

	public void setOpstina(int opstina) {
		this.opstina = opstina;
	}

	public int getPopisniKrug() {
		return popisniKrug;
	}

	public void setPopisniKrug(int popisniKrug) {
		this.popisniKrug = popisniKrug;
	}

	public int getZgrada() {
		return zgrada;
	}

	public void setZgrada(int zgrada) {
		this.zgrada = zgrada;
	}

	public int getStan() {
		return stan;
	}

	public void setStan(int stan) {
		this.stan = stan;
	}

	public int getDomacinstvo() {
		return domacinstvo;
	}

	public void setDomacinstvo(int domacinstvo) {
		this.domacinstvo = domacinstvo;
	}
}
