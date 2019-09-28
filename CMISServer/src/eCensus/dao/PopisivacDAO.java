package eCensus.dao;

import java.util.List;

import model.pracenje_popisa.izvjestaji_o_popisivacu.PopisniKrug;

public interface PopisivacDAO extends NaloziDAO {
	
	public boolean dodajPopisniKrug(int idPopisivaca, int idPopisnogKruga);
	
	public boolean obrisiPopisniKrug(int idPopisivaca, int idPopisnogKruga);
	
	public List<PopisniKrug> getListaPopisnihKrugovaPopisivaca(int idPopisivaca);
	
	public boolean azurirajOcjenu(int idPopisivaca, int idOGInstruktora, int ocjena);
	
}
