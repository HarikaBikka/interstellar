/**
 * 
 */
package za.co.discovery.assignment.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.discovery.assignment.service.algorithm.ShortestPathAlgorithm;
import za.co.discovery.assignment.service.algorithm.dto.PlanetDto;
import za.co.discovery.assignment.service.algorithm.dto.RouteDto;
import za.co.discovery.assignment.ws.service.Planet;
import za.co.discovery.assignment.ws.service.ShortestPathResponse;

/**
 * @author Harika
 *
 */
@Service
@PropertySource("classpath:application.properties")
public class ShortestPathServiceImpl implements ShortestPathService {

	private static final Logger LOGGER=Logger.getLogger(ShortestPathServiceImpl.class);
	
	@Autowired
	private ShortestPathAlgorithm shortestPathAlgorithm;
	
	@Value("${baseurl}")
	private String baseUrl;
	
	@Autowired
	private     RestTemplate restTemplate;
	 

	private static final ObjectMapper MAPPER = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * za.co.discovery.assignment.service.ShortestPathService#getShortestPath(za
	 * .co.discovery.assignment.service.algorithm.dto.PlanetDto,
	 * za.co.discovery.assignment.service.algorithm.dto.PlanetDto)
	 */
	public ShortestPathResponse getShortestPath(PlanetDto originPlanet, PlanetDto destPlanet, boolean isTraffic) {
		ShortestPathResponse response = new ShortestPathResponse();

		double shortestDistance = shortestPathAlgorithm.execute(originPlanet, destPlanet, getAllRoutes(), isTraffic);
		response.setShortestdistance(shortestDistance);

		List<PlanetDto> shortestPath = shortestPathAlgorithm.getPath(destPlanet);
		LOGGER.info(shortestPath);
		List<Planet> shortesPathPlanets = MAPPER.convertValue(shortestPath, new TypeReference<List<Planet>>() {
		});
		response.getShortestPath().addAll(shortesPathPlanets);
		return response;
	}

	public List<RouteDto> getAllRoutes() {
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/routes", String.class);

		JsonNode root;
		try {
			root = MAPPER.readTree(response.getBody());
			JsonNode results = root.path("results");

			return MAPPER.readValue(results.toString(), new TypeReference<List<RouteDto>>() {
			});
		} catch (IOException e) {
			LOGGER.error("Exception ",e);
		}
		return new ArrayList<RouteDto>();

	}

}
