package model.pracenje_popisa.izvjestaji_o_popisivacu;

public class Kontrolnik {

	protected int idPopisnogKruga;
	protected int idOpstine;

	protected int brojPopisanihStanova;
	protected int brojPopisanihDomacinstava;
	protected int brojDomacinstavaKojiSeBavePoljoprivredom;
	protected int brojPrisutnihClanovaDomacinstva;
	protected int brojOdsutnihClanovaDomacinstva;
	protected int brojNeodazvanihLica;
	protected int brojNeodazvanihDomacinstava;
	
	public Kontrolnik() {
		super();
	}
	
	public Kontrolnik(int idPopisnogKruga, int idOpstine, int brojPopisanihStanova, int brojPopisanihDomacinstava,
					  int brojDomacinstavaKojiSeBavePoljoprivredom, int brojPrisutnihClanovaDomacinstva,
					  int brojOdsutnihClanovaDomacinstva, int brojNeodazvanihLica, int brojNeodazvanihDomacinstava) {
		super();
		
		this.idPopisnogKruga = idPopisnogKruga;
		this.idOpstine = idOpstine;
		this.brojPopisanihStanova = brojPopisanihStanova;
		this.brojPopisanihDomacinstava = brojPopisanihDomacinstava;
		this.brojDomacinstavaKojiSeBavePoljoprivredom = brojDomacinstavaKojiSeBavePoljoprivredom;
		this.brojPrisutnihClanovaDomacinstva = brojPrisutnihClanovaDomacinstva;
		this.brojOdsutnihClanovaDomacinstva = brojOdsutnihClanovaDomacinstva;
		this.brojNeodazvanihLica = brojNeodazvanihLica;
		this.brojNeodazvanihDomacinstava = brojNeodazvanihDomacinstava;
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
	
	public int getBrojDomacinstavaKojiSeBavePoljoprivredom() {
		return brojDomacinstavaKojiSeBavePoljoprivredom;
	}
	
	public void setBrojDomacinstavaKojiSeBavePoljoprivredom(int brojDomacinstavaKojiSeBavePoljoprivredom) {
		this.brojDomacinstavaKojiSeBavePoljoprivredom = brojDomacinstavaKojiSeBavePoljoprivredom;
	}
	
	public int getBrojPrisutnihClanovaDomacinstva() {
		return brojPrisutnihClanovaDomacinstva;
	}
	
	public void setBrojPrisutnihClanovaDomacinstva(int brojPrisutnihClanovaDomacinstva) {
		this.brojPrisutnihClanovaDomacinstva = brojPrisutnihClanovaDomacinstva;
	}
	
	public int getBrojOdsutnihClanovaDomacinstva() {
		return brojOdsutnihClanovaDomacinstva;
	}
	
	public void setBrojOdsutnihClanovaDomacinstva(int brojOdsutnihClanovaDomacinstva) {
		this.brojOdsutnihClanovaDomacinstva = brojOdsutnihClanovaDomacinstva;
	}
	
	public int getBrojNeodazvanihLica() {
		return brojNeodazvanihLica;
	}
	
	public void setBrojNeodazvanihLica(int brojNeodazvanihLica) {
		this.brojNeodazvanihLica = brojNeodazvanihLica;
	}
	
	public int getBrojNeodazvanihDomacinstava() {
		return brojNeodazvanihDomacinstava;
	}
	
	public void setBrojNeodazvanihDomacinstava(int brojNeodazvanihDomacinstava) {
		this.brojNeodazvanihDomacinstava = brojNeodazvanihDomacinstava;
	}
	
}
