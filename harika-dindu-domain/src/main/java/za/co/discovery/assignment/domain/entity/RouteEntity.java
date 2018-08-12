/**
 * 
 */
package za.co.discovery.assignment.domain.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author Harika
 *
 */
@Entity
@Table(name = "ROUTES")
@Inheritance( strategy = InheritanceType.JOINED )
public class RouteEntity extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private PlanetRoutesId planetRoute;
	
	@Column(name = "DISTANCE", nullable = false)
	private double distance;

	
	public RouteEntity() {
		super();
	}

	public RouteEntity(PlanetRoutesId planetRoute, double distance) {
		super();
		this.planetRoute = planetRoute;
		this.distance = distance;
	}

	public PlanetRoutesId getPlanetRoute() {
		return planetRoute;
	}

	public void setPlanetRoute(PlanetRoutesId planetRoute) {
		this.planetRoute = planetRoute;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "RouteEntity [planetRoute=" + planetRoute + ", distance=" + distance + "]";
	}

	 

}
