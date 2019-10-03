package eCensus.dao;

import java.util.List;

import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;

public interface AktivnostDAO {

	public boolean azurirajAktivnost(int idPopisivaca, DnevnaAktivnost dnevnaAktivnost);
	
	public List<DnevnaAktivnost> getListaDnevnihAktivnosti(int idPopisivaca);
	
}
