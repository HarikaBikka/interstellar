/**
 * 
 */
package za.co.discovery.assignment.service.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

import za.co.discovery.assignment.service.algorithm.dto.PlanetDto;
import za.co.discovery.assignment.service.algorithm.dto.PlanetRouteDto;
import za.co.discovery.assignment.service.algorithm.dto.RouteDto;

/**
 * @author Harika
 * 
 */

@Component
public class ShortestPathAlgorithm {

	private List<RouteDto> routes;
	private Set<PlanetDto> settledNodes;
	private Set<PlanetDto> unSettledNodes;
	private Map<PlanetDto, PlanetDto> predecessors;
	private Map<PlanetDto, Double> distance;
	private boolean isTraffic;

	public Double execute(PlanetDto sourcePlanet, PlanetDto destPlanet, List<RouteDto> routes, boolean isTraffic) {
		this.routes = new ArrayList<RouteDto>(routes);
		settledNodes = new HashSet<PlanetDto>();
		predecessors = new HashMap<PlanetDto, PlanetDto>();

		distance = new HashMap<PlanetDto, Double>();
		distance.put(sourcePlanet, Double.valueOf(0.0D));

		unSettledNodes = new HashSet<PlanetDto>();
		unSettledNodes.add(sourcePlanet);

		this.isTraffic = isTraffic;
		while (!unSettledNodes.isEmpty()) {
			PlanetDto planet = getMinimum(unSettledNodes);
			settledNodes.add(planet);
			unSettledNodes.remove(planet);
			findMinimalDistances(planet);
		}

		return distance.get(destPlanet);
	}

	/*
	 * Calculating minimal distances between child nodes from the source node
	 */
	private void findMinimalDistances(PlanetDto planet) {
		List<PlanetDto> adjacentNodes = getNeighbors(planet);

		for (PlanetDto destPlanet : adjacentNodes) {
			double distanceBetSourceAndDestPlanet = getDistance(planet, destPlanet);
			if (getShortestDistance(destPlanet).doubleValue() > getShortestDistance(planet).doubleValue()
					+ distanceBetSourceAndDestPlanet) {
				distance.put(destPlanet,
						Double.valueOf(getShortestDistance(planet).doubleValue() + distanceBetSourceAndDestPlanet));
				predecessors.put(destPlanet, planet);
				unSettledNodes.add(destPlanet);
			}
		}
	}

	/*
	 * Distance between source planet and child planet with and without traffic
	 */
	private double getDistance(PlanetDto sourcePlanet, PlanetDto destPlanet) {
		for (RouteDto route : this.routes) {

			PlanetRouteDto planetRoute = route.getPlanetRoute();

			PlanetDto originPlanet = planetRoute.getOriginPlanet();
			PlanetDto destinationPlanet = planetRoute.getDestPlanet();

			if ((originPlanet.getPlanetNode().equals(sourcePlanet.getPlanetNode()))
					&& (destinationPlanet.getPlanetNode().equals(destPlanet.getPlanetNode()))) {
				// if to be calculated with traffic
				while (isTraffic)
					return route.getDistance() + route.getTrafficDelay();
				
				// if to be calculated without traffic
				return route.getDistance();
			}
		}
		throw new InterStellarServiceException("Should not happen");
	}

	/*
	 * Getting the adjacent planets from the source
	 */
	private List<PlanetDto> getNeighbors(PlanetDto planet) {
		List<PlanetDto> neighbors = new ArrayList<PlanetDto>();

		for (RouteDto route : this.routes) {
			PlanetRouteDto planetRoute = route.getPlanetRoute();
			PlanetDto originPlanet = planetRoute.getOriginPlanet();

			if ((originPlanet.getPlanetNode().equals(planet.getPlanetNode()))
					&& (!isSettled(planetRoute.getDestPlanet()))) {
				neighbors.add(planetRoute.getDestPlanet());
				planet.setPlanetName(originPlanet.getPlanetName());
			}
		}
		return neighbors;

	}

	/*
	 * checking if the planet's distance is greater than the previous planets
	 */
	private PlanetDto getMinimum(Set<PlanetDto> planets) {
		PlanetDto minimum = new PlanetDto();
		for (PlanetDto planet : planets) {
			if (getShortestDistance(planet).doubleValue() < getShortestDistance(minimum).doubleValue()) {
				minimum = planet;
			}
		}

		return minimum;
	}

	private boolean isSettled(PlanetDto planet) {
		return settledNodes.contains(planet);
	}

	private Double getShortestDistance(PlanetDto planet) {
		Double routeDistance = distance.get(planet);
		if (routeDistance == null) {
			return Double.valueOf(Double.MAX_VALUE);
		}
		return routeDistance;
	}

	public LinkedList<PlanetDto> getPath(PlanetDto planet) {
		LinkedList<PlanetDto> path = new LinkedList<PlanetDto>();

		if (predecessors.get(planet) == null) {
			return new LinkedList<PlanetDto>();
		}
		path.add(planet);
		while (predecessors.get(planet) != null) {
			planet = (PlanetDto) predecessors.get(planet);
			path.add(planet);
		}

		Collections.reverse(path);
		return path;
	}
}
