/**
 * 
 */
package za.co.discovery.assignment.service.algorithm.dto;

import java.io.Serializable;

/**
 * @author Harika
 *
 */
public class PlanetDto implements Serializable {


	private static final long serialVersionUID = 1L;
	private String planetNode;
	private String planetName;

	public PlanetDto() {
		super();
	}
	public PlanetDto(String planetNode) {
		super();
		this.planetNode = planetNode;
	}
	public PlanetDto(String planetNode, String planetName) {
		this.planetNode = planetNode;
		this.planetName = planetName;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	public String getPlanetNode() {
		return planetNode;
	}

	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}

	public String toString() {
		return "PlanetDto [planetNode=" + planetNode + ", planetName=" + planetName + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((planetNode == null) ? 0 : planetNode.hashCode());
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
		PlanetDto other = (PlanetDto) obj;
		if (planetNode == null) {
			if (other.planetNode != null)
				return false;
		} else if (!planetNode.equals(other.planetNode))
			return false;
		return true;
	}

	 

	 
}
