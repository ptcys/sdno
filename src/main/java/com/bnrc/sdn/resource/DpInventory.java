package com.bnrc.sdn.resource;


public class DpInventory {
	private Integer dpId;
	
	 /**
     * 
     * @return
     *     The dpId
     */
  
    public Integer getDpId() {
        return dpId;
    }

    /**
     * 
     * @param dpId
     *     The dpId
     */
  
    public void setDpId(Integer dpId) {
        this.dpId = dpId;
    }
    
	@Override
	public String toString() {
		return "{\"dpId\":" + dpId + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dpId == null) ? 0 : dpId.hashCode());
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
		DpInventory other = (DpInventory) obj;
		if (dpId == null) {
			if (other.dpId != null)
				return false;
		} else if (!dpId.equals(other.dpId))
			return false;
		return true;
	}

}
