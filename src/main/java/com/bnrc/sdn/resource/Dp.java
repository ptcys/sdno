package com.bnrc.sdn.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="dp")
public class Dp {
	
	@Id
	private String cid;
	
	private Integer dpId;
    
	private String ip;
    
	private List<Integer> ports = new ArrayList<Integer>();
	
    /**
     * 
     * @return
     *     The cid
     */
	public String getCid() {
        return cid;
    }

    /**
     * 
     * @param cid
     *     The cid
     */
    public void setCid(String cid) {
        this.cid = cid;
    }
    
   
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

    /**
     * 
     * @return
     *     The ip
     */
 
    public String getIp() {
        return ip;
    }

    /**
     * 
     * @param ip
     *     The ip
     */
   
    public void setip(String ip) {
        this.ip = ip;
    }

    
    /**
     * 
     * @return
     *     The ports
     */
  
    public List<Integer> getPorts() {
        return ports;
    }

    /**
     * 
     * @param ports
     *     The ports
     */
  
    public void setPorts(List<Integer> ports) {
        this.ports = ports;
    }
    
	@Override
	public String toString() {
		return "Dp [ cid="+cid+",dpId=" + dpId + ", ip=" + ip + ", ports=" + ports + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cid == null) ? 0 : cid.hashCode());
		result = prime * result + ((dpId == null) ? 0 : dpId.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((ports == null) ? 0 : ports.hashCode());
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
		Dp other = (Dp) obj;
		if (cid == null) {
			if (other.cid != null)
				return false;
		} else if (!cid.equals(other.cid))
			return false;
		if (dpId == null) {
			if (other.dpId != null)
				return false;
		} else if (!dpId.equals(other.dpId))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (ports == null) {
			if (other.ports != null)
				return false;
		} else if (!ports.equals(other.ports))
			return false;
		return true;
	}
}
