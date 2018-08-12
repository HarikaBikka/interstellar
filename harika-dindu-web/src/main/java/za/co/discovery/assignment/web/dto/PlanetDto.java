/**
 * 
 */
package za.co.discovery.assignment.web.dto;

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

	public String getPlanetNode() {
		return planetNode;
	}

	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}

	public String getPlanetName() {
		return planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	@Override
	public String toString() {
		return "PlanetDto [planetNode=" + planetNode + ", planetName=" + planetName + "]";
	}

}
