/**
 * 
 */
package za.co.discovery.assignment.web.service;

import java.util.List;
import java.util.Map;

import za.co.discovery.assignment.web.dto.PlanetDto;

/**
 * @author Harika
 *
 */
public interface InterstellarService {

	List<PlanetDto> getAllPlanets();
	Map<Double, List<PlanetDto>> getShotestPath(String sourcePlanetNode, String destPlanetNode, boolean isTraffic);
}
