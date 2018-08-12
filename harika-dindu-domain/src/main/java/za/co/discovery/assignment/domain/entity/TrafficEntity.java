/**
 * 
 */
package za.co.discovery.assignment.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.PrimaryKeyJoinColumns;
import javax.persistence.Table;

/**
 * @author Harika
 *
 */
@Entity
@Table(name = "TRAFFIC")
@PrimaryKeyJoinColumns({
    @PrimaryKeyJoinColumn(name="ROUTE_ID", referencedColumnName="ROUTE_ID" ),
    @PrimaryKeyJoinColumn(name="PLANET_ORIGIN", referencedColumnName="PLANET_ORIGIN"),
    @PrimaryKeyJoinColumn(name="PLANET_DESTINATION", referencedColumnName="PLANET_DESTINATION")
})
public class TrafficEntity extends RouteEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "DELAY", nullable = false)
	private double trafficDelay;

	public TrafficEntity() {
		super();
	}

	public TrafficEntity(PlanetRoutesId planetRoute, double distance,double trafficDelay) {
		super(planetRoute, distance);
		this.trafficDelay = trafficDelay;
	}

	public double getTrafficDelay() {
		return trafficDelay;
	}

	public void setTrafficDelay(double trafficDelay) {
		this.trafficDelay = trafficDelay;
	}

	@Override
	public String toString() {
		return "TrafficEntity [trafficDelay=" + trafficDelay + ", getPlanetRoute()=" + getPlanetRoute()
				+ ", getDistance()=" + getDistance() + "]";
	}


	 

}
