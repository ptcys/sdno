
package com.bnrc.sdn.resource.topo;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.data.mongodb.core.mapping.Field;

public class Node {

	@Field("node-id")
	private String nodeId = new String();
	@Field("termination-point")
	private List<TerminationPoint> terminationPoint = new ArrayList<TerminationPoint>();
	@Field("hostTrackerService:addresses")
	private List<HostTrackerServiceAddress> hostTrackerServiceAddresses = new ArrayList<HostTrackerServiceAddress>();
	@Field("hostTrackerService:id")
	private String hostTrackerServiceId = new String();

	@Field("hostTrackerService:attachmentPoints")
	private List<HostTrackerServiceAttachmentPoint> hostTrackerServiceAttachmentPoints = new ArrayList<HostTrackerServiceAttachmentPoint>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Node() {
	}

	/**
	 * 
	 * @param hostTrackerServiceId
	 * @param nodeId
	 * @param terminationPoint
	 * @param hostTrackerServiceAttachmentPoints
	 * @param hostTrackerServiceAddresses
	 */
	public Node(String nodeId, List<TerminationPoint> terminationPoint,
			List<HostTrackerServiceAddress> hostTrackerServiceAddresses, String hostTrackerServiceId,
			List<HostTrackerServiceAttachmentPoint> hostTrackerServiceAttachmentPoints) {
		this.nodeId = nodeId;
		this.terminationPoint = terminationPoint;
		this.hostTrackerServiceAddresses = hostTrackerServiceAddresses;
		this.hostTrackerServiceId = hostTrackerServiceId;
		this.hostTrackerServiceAttachmentPoints = hostTrackerServiceAttachmentPoints;
	}

	/**
	 * 
	 * @return The nodeId
	 */
	public String getNodeId() {
		return nodeId;
	}

	/**
	 * 
	 * @param nodeId
	 *            The nodeId
	 */
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	/**
	 * 
	 * @return The terminationPoint
	 */
	public List<TerminationPoint> getTerminationPoint() {
		return terminationPoint;
	}

	/**
	 * 
	 * @param terminationPoint
	 *            The termination-point
	 */
	public void setTerminationPoint(List<TerminationPoint> terminationPoint) {
		this.terminationPoint = terminationPoint;
	}

	/**
	 * 
	 * @return The hostTrackerServiceAddresses
	 */
	public List<HostTrackerServiceAddress> getHostTrackerServiceAddresses() {
		return hostTrackerServiceAddresses;
	}

	/**
	 * 
	 * @param hostTrackerServiceAddresses
	 *            The hostTracker-service:addresses
	 */
	public void setHostTrackerServiceAddresses(List<HostTrackerServiceAddress> hostTrackerServiceAddresses) {
		this.hostTrackerServiceAddresses = hostTrackerServiceAddresses;
	}

	/**
	 * 
	 * @return The hostTrackerServiceId
	 */
	public String getHostTrackerServiceId() {
		return hostTrackerServiceId;
	}

	/**
	 * 
	 * @param hostTrackerServiceId
	 *            The hostTracker-service:id
	 */
	public void setHostTrackerServiceId(String hostTrackerServiceId) {
		this.hostTrackerServiceId = hostTrackerServiceId;
	}

	/**
	 * 
	 * @return The hostTrackerServiceAttachmentPoints
	 */
	public List<HostTrackerServiceAttachmentPoint> getHostTrackerServiceAttachmentPoints() {
		return hostTrackerServiceAttachmentPoints;
	}

	/**
	 * 
	 * @param hostTrackerServiceAttachmentPoints
	 *            The hostTracker-service:attachment-points
	 */
	public void setHostTrackerServiceAttachmentPoints(
			List<HostTrackerServiceAttachmentPoint> hostTrackerServiceAttachmentPoints) {
		this.hostTrackerServiceAttachmentPoints = hostTrackerServiceAttachmentPoints;
	}

	@Override
	public String toString() {
		return "Node [nodeId=" + nodeId + ", terminationPoint=" + terminationPoint + ", hostTrackerServiceAddresses="
				+ hostTrackerServiceAddresses + ", hostTrackerServiceId=" + hostTrackerServiceId
				+ ", hostTrackerServiceAttachmentPoints=" + hostTrackerServiceAttachmentPoints + "]";
	}

	@Override
	public int hashCode() {
		int hashCode=0;
		hashCode=hashCode+ nodeId.hashCode()/5;
		for(int i=0;i<terminationPoint.size();i++){
			hashCode = hashCode+terminationPoint.get(i).hashCode()/(5*terminationPoint.size());
		}
		for(int i=0;i<hostTrackerServiceAddresses.size();i++){
			hashCode = hashCode+hostTrackerServiceAddresses.get(i).hashCode()/(5*hostTrackerServiceAddresses.size());
		}
		hashCode = hashCode+hostTrackerServiceId.hashCode()/5;
		for(int i=0;i<hostTrackerServiceAttachmentPoints.size();i++){
			hashCode = hashCode+hostTrackerServiceAttachmentPoints.get(i).hashCode()/(5*hostTrackerServiceAttachmentPoints.size());
		}
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Node) == false) {
			return false;
		}
		Node rhs = ((Node) other);
		return new EqualsBuilder().append(nodeId, rhs.nodeId).append(terminationPoint, rhs.terminationPoint)
				.append(hostTrackerServiceAddresses, rhs.hostTrackerServiceAddresses)
				.append(hostTrackerServiceId, rhs.hostTrackerServiceId)
				.append(hostTrackerServiceAttachmentPoints, rhs.hostTrackerServiceAttachmentPoints).isEquals();
	}

}
