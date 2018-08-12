/**
 * 
 */
package za.co.discovery.assignment.domain.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Harika
 *
 */
@Embeddable
public class PlanetRoutesId extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final String PLANET_NODE = "PLANET_NODE";

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROUTE_ID", unique = true)
	private int routeId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PLANET_ORIGIN", referencedColumnName = PLANET_NODE, updatable = false, insertable = false)
	private PlanetEntity originPlanet;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "PLANET_DESTINATION", referencedColumnName = PLANET_NODE, updatable = false, insertable = false)
	private PlanetEntity destPlanet;

	public PlanetRoutesId() {
		super();
	}

	public PlanetRoutesId(int routeId, PlanetEntity originPlanet, PlanetEntity destPlanet) {
		super();
		this.routeId = routeId;
		this.originPlanet = originPlanet;
		this.destPlanet = destPlanet;
	}

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public PlanetEntity getOriginPlanet() {
		return originPlanet;
	}

	public void setOriginPlanet(PlanetEntity originPlanet) {
		this.originPlanet = originPlanet;
	}

	public PlanetEntity getDestPlanet() {
		return destPlanet;
	}

	public void setDestPlanet(PlanetEntity destPlanet) {
		this.destPlanet = destPlanet;
	}

	@Override
	public String toString() {
		return "PlanetRoutesId [routeId=" + routeId + ", originPlanet=" + originPlanet + ", destPlanet=" + destPlanet
				+ "]";
	}

}
