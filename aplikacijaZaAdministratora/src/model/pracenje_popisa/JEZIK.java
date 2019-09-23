package model.pracenje_popisa;

/**
 * Jezik aplikacije
 */
public enum JEZIK
{
    SRPSKI("Srpski"),
    BOSANSKI("Bosanski"),
    HRVATSKI("Hrvatski");
    
    private String jezik;
    private JEZIK(String jezik) {
    	this.jezik = jezik;
    }
    
    public String toString() {
    	return jezik;
    }
    
    public String getValue() {
        return jezik;
    }
    
    public static JEZIK getJEZIK(String jezik) {
    	for(JEZIK value : JEZIK.values()) {
    		if(value.getValue().equalsIgnoreCase(jezik)) {
    			return value;
    		}
    	}
    	return null;
    }
}