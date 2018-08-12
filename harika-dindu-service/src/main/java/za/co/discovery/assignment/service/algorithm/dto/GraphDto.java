package za.co.discovery.assignment.service.algorithm.dto;

import java.io.Serializable;
import java.util.List;

public class GraphDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<PlanetDto> planets;
	private List<RouteDto> routes;

	public GraphDto(List<PlanetDto> planets, List<RouteDto> routes) {
		this.planets = planets;
		this.routes = routes;
	}

	public List<PlanetDto> getPlanets() {
		return planets;
	}

	public List<RouteDto> getRoutes() {
		return routes;
	}
}
