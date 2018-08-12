/**
 * 
 */
package za.co.discovery.assignment.service.algorithm.dto;

import java.io.Serializable;

/**
 * @author Harika
 *
 */
public class PlanetRouteDto implements Serializable {

	 private static final long serialVersionUID = 1;
	    private int routeId;
	    private PlanetDto originPlanet;
	    private PlanetDto destPlanet;

	    public PlanetRouteDto() {
	    }

	    public PlanetRouteDto(int routeId, PlanetDto originPlanet, PlanetDto destPlanet) {
	        this.routeId = routeId;
	        this.originPlanet = originPlanet;
	        this.destPlanet = destPlanet;
	    }

	    public int getRouteId() {
	        return this.routeId;
	    }

	    public void setRouteId(int routeId) {
	        this.routeId = routeId;
	    }

	    public PlanetDto getOriginPlanet() {
	        return this.originPlanet;
	    }

	    public void setOriginPlanet(PlanetDto originPlanet) {
	        this.originPlanet = originPlanet;
	    }

	    public PlanetDto getDestPlanet() {
	        return this.destPlanet;
	    }

	    public void setDestPlanet(PlanetDto destPlanet) {
	        this.destPlanet = destPlanet;
	    }
}
