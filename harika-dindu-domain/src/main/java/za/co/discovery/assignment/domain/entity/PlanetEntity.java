package za.co.discovery.assignment.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PLANET")
public class PlanetEntity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "PLANET_NODE", unique = true)
	private String planetNode;

	@Column(name = "PLANET_NAME", nullable = false)
	private String planetName;

	/*@OneToMany(mappedBy = "planetRoute.originPlanet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<RouteEntity> routes;
*/
	public String getPlanetNode() {
		return planetNode;
	}

	public void setPlanetNode(String planetNode) {
		this.planetNode = planetNode;
	}

	public String getPlanetName() {
		return planetName;
	}

	public PlanetEntity() {
		super();
	}

	public PlanetEntity(String planetNode, String planetName) {
		super();
		this.planetNode = planetNode;
		this.planetName = planetName;
	}

	public void setPlanetName(String planetName) {
		this.planetName = planetName;
	}

	 
	@Override
	public String toString() {
		return "Planets [planetNode=" + planetNode + ", planetName=" + planetName + "]";
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
		PlanetEntity other = (PlanetEntity) obj;
		if (planetNode == null) {
			if (other.planetNode != null)
				return false;
		} else if (!planetNode.equals(other.planetNode))
			return false;
		return true;
	}
}
