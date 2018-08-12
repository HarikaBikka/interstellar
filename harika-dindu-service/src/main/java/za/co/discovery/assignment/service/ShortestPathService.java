/**
 * 
 */
package za.co.discovery.assignment.service;

import za.co.discovery.assignment.service.algorithm.dto.PlanetDto;
import za.co.discovery.assignment.ws.service.ShortestPathResponse;

/**
 * @author Harika
 *
 */
public interface ShortestPathService {

	ShortestPathResponse getShortestPath(PlanetDto originPlanet, PlanetDto destPlanet, boolean isTraffic);
}
