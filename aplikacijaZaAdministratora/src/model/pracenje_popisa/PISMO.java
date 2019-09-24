package model.pracenje_popisa;

/**
 * Pismo aplikacije
 */
public enum PISMO
{
    LATINICA("Latinica"),
    CIRILICA("Cirilica");
	
	private String pismo;
	private PISMO(String pismo) {
		this.pismo = pismo;
	}
	
	public String toString() {
		return pismo;
	}
	
	public String getValue() {
        return pismo;
    }
    
    public static PISMO getPISMO(String pismo) {
    	for(PISMO value : PISMO.values()) {
    		if(value.getValue().equalsIgnoreCase(pismo)) {
    			return value;
    		}
    	}
    	return null;
    }
}