
package com.bnrc.sdn.resource.topo;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.springframework.data.annotation.Id;

public class Topo {

	@Id
	private String id = new String();
	private List<Link> link = new ArrayList<Link>();
	private List<Node> node = new ArrayList<Node>();

	/**
	 * No args constructor for use in serialization
	 * 
	 */
	public Topo() {
	}

	/**
	 * 
	 * @param node
	 * @param link
	 */
	public Topo(List<Link> link, List<Node> node) {
		this.link = link;
		this.node = node;
	}

	/**
	 * 
	 * @return The link
	 */
	public List<Link> getLink() {
		return link;
	}

	/**
	 * 
	 * @param link
	 *            The link
	 */
	public void setLink(List<Link> link) {
		this.link = link;
	}

	/**
	 * 
	 * @return The node
	 */
	public List<Node> getNode() {
		return node;
	}

	/**
	 * 
	 * @param node
	 *            The node
	 */
	public void setNode(List<Node> node) {
		this.node = node;
	}

	@Override
	public String toString() {
		return "Topo [id=" + id + " link=" + link + ",\n node=" + node + "]";// "link="
																				// +
																				// link
																				// +
																				// ",
	}

	@Override
	public int hashCode() {
		int hashCode=0;
		hashCode = hashCode + id.hashCode()/3;
		for(int i=0;i<link.size();i++){
			hashCode = hashCode + link.get(i).hashCode()/(3*link.size());
		}
		for(int i=0;i<node.size();i++){
			hashCode = hashCode +node.get(i).hashCode()/(3*node.size());
		}
		System.out.println("topo hash code = "+ hashCode);
		return hashCode;
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Topo) == false) {
			return false;
		}
		Topo rhs = ((Topo) other);
		return new EqualsBuilder().append(link, rhs.link).append(node, rhs.node).isEquals();
	}

}
