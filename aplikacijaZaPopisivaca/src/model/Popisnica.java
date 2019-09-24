package model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Popisnica implements Serializable{
	protected int idObrasca;
	protected int idEntiteta;
	protected int idOpstine;
	protected int idPopisnogKruga;
	protected int idStana;
	protected int idDomacinstva;
	
	protected Map<String, List<String>> odgovoriNaPitanja;
	
	public Popisnica() {
		odgovoriNaPitanja = new HashMap<String, List<String>>();
	}
	
	public Popisnica(int idObrasca, int idEntiteta, int idOpstine, int idPopisnogKruga, int idStana,
			int idDomacinstva) {
		super();
		this.idObrasca = idObrasca;
		this.idEntiteta = idEntiteta;
		this.idOpstine = idOpstine;
		this.idPopisnogKruga = idPopisnogKruga;
		this.idStana = idStana;
		this.idDomacinstva = idDomacinstva;
	}

	public int getIdObrasca() {
        return idObrasca;
    }

    public void setIdObrasca(int idObrasca) {
        this.idObrasca = idObrasca;
    }

    public int getIdEntiteta() {
        return idEntiteta;
    }

    public void setIdEntiteta(int idEntiteta) {
        this.idEntiteta = idEntiteta;
    }

    public int getIdOpstine() {
        return idOpstine;
    }

    public void setIdOpstine(int idOpstine) {
        this.idOpstine = idOpstine;
    }

    public int getIdPopisnogKruga() {
        return idPopisnogKruga;
    }

    public void setIdPopisnogKruga(int idPopisnogKruga) {
        this.idPopisnogKruga = idPopisnogKruga;
    }

    public int getIdStana() {
        return idStana;
    }

    public void setIdStana(int idStana) {
        this.idStana = idStana;
    }

    public int getIdDomacinstva() {
        return idDomacinstva;
    }

    public void setIdDomacinstva(int idDomacinstva) {
        this.idDomacinstva = idDomacinstva;
    }
    
	public Map<String, List<String>> getOdgovoriNaPitanja() {
        return odgovoriNaPitanja;
    }

    public void setOdgovoriNaPitanja(Map<String, List<String>> odgovoriNaPitanja) {
        this.odgovoriNaPitanja = odgovoriNaPitanja;
    }
}
