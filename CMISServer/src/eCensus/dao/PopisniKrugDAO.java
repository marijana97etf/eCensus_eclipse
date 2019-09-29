package eCensus.dao;

import java.util.List;

import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public interface PopisniKrugDAO {

	public boolean dodajPopisniKrug(PopisniKrug popisniKrug);
	
	public boolean obrisiPopisniKrug(int idPopisniKrug, int idOpstine);
	
	public List<PopisniKrug> getListaPopisnihKrugova(String grad, int IdOpstine);
	
	public PopisniKrug getPopisniKrug(int idPopisnogKruga, int idOpstine);
	
}
