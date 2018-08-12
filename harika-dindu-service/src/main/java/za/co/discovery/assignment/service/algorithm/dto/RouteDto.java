/**
 * 
 */
package za.co.discovery.assignment.service.algorithm.dto;

import java.io.Serializable;

/**
 * @author Harika
 *
 */
public class RouteDto implements Serializable {
	
	private static final long serialVersionUID = 4403335298600682774L;
	
	private PlanetRouteDto planetRoute;
	private double trafficDelay;
	private double distance;

	public RouteDto() {
	}

	public RouteDto(PlanetRouteDto planetRoute, double trafficDelay, double distance) {
		this.planetRoute = planetRoute;
		this.trafficDelay = trafficDelay;
		this.distance = distance;
	}

	public double getTrafficDelay() {
		return this.trafficDelay;
	}

	public void setTrafficDelay(double trafficDelay) {
		this.trafficDelay = trafficDelay;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public PlanetRouteDto getPlanetRoute() {
		return this.planetRoute;
	}

	public void setPlanetRoute(PlanetRouteDto planetRoute) {
		this.planetRoute = planetRoute;
	}

	public String toString() {
		return "RouteDto [planetRoute=" + this.planetRoute + ", trafficDelay=" + this.trafficDelay + ", distance="
				+ this.distance + "]";
	}
}