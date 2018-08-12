/**
 * 
 */
package za.co.discovery.assignment.service.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;

import za.co.discovery.assignment.service.algorithm.dto.PlanetDto;
import za.co.discovery.assignment.service.algorithm.dto.PlanetRouteDto;
import za.co.discovery.assignment.service.algorithm.dto.RouteDto;

/**
 * @author Harika
 *
 */
public class CalculateShortestPathTestCase {

	private List<PlanetDto> planets;
	private List<RouteDto> routes;

	@Test
	public void calculateShortestPathTest() {
		planets = new ArrayList<PlanetDto>();
		routes = new ArrayList<RouteDto>();
		for (int i = 0; i < 11; i++) {
			PlanetDto location = new PlanetDto("NodeID_" + i, "NodeNAME_" + i);
			planets.add(location);
		}

		PlanetDto sourcePlanet = planets.get(0);
		PlanetDto destPlanet = planets.get(9);

		addLane(new PlanetRouteDto(1, sourcePlanet, planets.get(1)), 85, 20);
		addLane(new PlanetRouteDto(2, sourcePlanet, planets.get(2)), 217, 20);
		addLane(new PlanetRouteDto(3, sourcePlanet, planets.get(4)), 173, 20);
		addLane(new PlanetRouteDto(4, planets.get(2), planets.get(6)), 186, 20);
		addLane(new PlanetRouteDto(5, planets.get(2), planets.get(7)), 103, 20);
		addLane(new PlanetRouteDto(6, planets.get(3), planets.get(7)), 183, 20);
		addLane(new PlanetRouteDto(7, planets.get(5), planets.get(8)), 250, 20);
		addLane(new PlanetRouteDto(8, planets.get(8), planets.get(9)), 84, 20);
		addLane(new PlanetRouteDto(9, planets.get(7), planets.get(9)), 167, 20);
		addLane(new PlanetRouteDto(10, planets.get(4), planets.get(9)), 502, 20);
		addLane(new PlanetRouteDto(11, planets.get(9), destPlanet), 40, 20);
		addLane(new PlanetRouteDto(12, planets.get(1), destPlanet), 600, 20);

		// Lets check from location Loc_1 to Loc_10

		ShortestPathAlgorithm dijkstra = new ShortestPathAlgorithm();
		System.out.println(dijkstra.execute(sourcePlanet, destPlanet, routes, true));

		Queue<PlanetDto> path = dijkstra.getPath(destPlanet);

		Assert.assertNotNull(path);
		Assert.assertTrue(path.size() > 0);

		for (PlanetDto planet : path) {
			System.out.println(planet);
		}
		// System.out.println(dijkstra.);
	}

	private void addLane(PlanetRouteDto planetRoute, double trafficDelay, double distance) {
		RouteDto lane = new RouteDto(planetRoute, trafficDelay, distance);
		routes.add(lane);
	}
}
