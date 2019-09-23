package model.pracenje_popisa.izvjestaji_o_popisivacu;

public class PopisniKrug {

    protected String opstina,grad;
    protected byte[] slikaBytes;

	public PopisniKrug(String opstina, String grad) {
		super();
		this.opstina = opstina;
		this.grad = grad;
	}

	public PopisniKrug(String opstina, String grad, byte[] slikaBytes) {
		super();
		this.opstina = opstina;
		this.grad = grad;
		this.slikaBytes = slikaBytes;
	}

	public byte[] getSlikaBytes() {
		return slikaBytes;
	}

	public void setSlikaBytes(byte[] slikaBytes) {
		this.slikaBytes = slikaBytes;
	}

	public String getOpstina() {
		return opstina;
	}

	public String getGrad() {
		return grad;
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
