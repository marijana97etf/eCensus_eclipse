package eCensus.dao;

import model.pracenje_popisa.izvjestaji_o_popisivacu.DnevnaAktivnost;

public interface AktivnostDAO {

	public boolean azurirajAktivnost(int idPopisivaca, DnevnaAktivnost dnevnaAktivnost);
	
}
