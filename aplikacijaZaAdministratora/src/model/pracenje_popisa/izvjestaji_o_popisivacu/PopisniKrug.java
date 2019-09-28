package model.pracenje_popisa.izvjestaji_o_popisivacu;

import java.util.List;

public class PopisniKrug {

	protected int id;
    protected String opstina,grad;
    protected byte[] slikaBytes;
    protected List<String> ulice;

    public PopisniKrug() {}
	public PopisniKrug(String opstina, String grad, List<String> ulice) {
		super();
		this.opstina = opstina;
		this.grad = grad;
		this.ulice = ulice;
	}

	public PopisniKrug(String opstina, String grad, List<String> ulice, byte[] slikaBytes) {
		super();
		this.opstina = opstina;
		this.grad = grad;
		this.ulice = ulice;
		this.slikaBytes = slikaBytes;
	}

	public byte[] getSlikaBytes() {
		return slikaBytes;
	}

	public void setSlikaBytes(byte[] slikaBytes) {
		this.slikaBytes = slikaBytes;
	}

	public List<String> getUlice() {
		return ulice;
	}
	
	public void setUlice(List<String> ulice) {
		this.ulice = ulice;
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
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grad == null) ? 0 : grad.hashCode());
		result = prime * result + ((opstina == null) ? 0 : opstina.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PopisniKrug other = (PopisniKrug) obj;
		if (grad == null) {
			if (other.grad != null)
				return false;
		} else if (!grad.equals(other.grad))
			return false;
		if (opstina == null) {
			if (other.opstina != null)
				return false;
		} else if (!opstina.equals(other.opstina))
			return false;
		return true;
	}


}
