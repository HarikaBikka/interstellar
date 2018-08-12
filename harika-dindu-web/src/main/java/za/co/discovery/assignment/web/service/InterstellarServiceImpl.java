/**
 * 
 */
package za.co.discovery.assignment.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.discovery.assignment.web.dto.PlanetDto;
import za.co.discovery.assignment.ws.client.ObjectFactory;
import za.co.discovery.assignment.ws.client.ShortestPathRequest;
import za.co.discovery.assignment.ws.client.ShortestPathResponse;

/**
 * @author Harika
 *
 */
@Service
@PropertySource("classpath:application.properties")
public class InterstellarServiceImpl implements InterstellarService {

	private static final Logger LOGGER=Logger.getLogger(InterstellarServiceImpl.class);
	@Autowired
	private WebServiceTemplate webServiceTemplate;

	@Autowired
	private   RestTemplate restTemplate;
	
	@Value("${baseurl}")
	private String baseUrl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * za.co.discovery.assignment.web.service.InterstellarService#getShotestPath
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public Map<Double, List<PlanetDto>> getShotestPath(String sourcePlanetNode, String destPlanetNode,
			boolean isTraffic) {

		ShortestPathRequest shortestPathRequest = new ObjectFactory().createShortestPathRequest();

		shortestPathRequest.setDestPlanet(sourcePlanetNode);
		shortestPathRequest.setOriginPlanet(destPlanetNode);
		shortestPathRequest.setTraffic(isTraffic);

		ShortestPathResponse response = (ShortestPathResponse) webServiceTemplate
				.marshalSendAndReceive(shortestPathRequest);

		List<PlanetDto> shortesPathPlanets = new ObjectMapper().convertValue(response.getShortestPath(),
				new TypeReference<List<PlanetDto>>() {
				});

		Map<Double, List<PlanetDto>> shotestPathData = new HashMap<>();
		shotestPathData.put(response.getShortestdistance(), shortesPathPlanets);

		return shotestPathData;
	}

	@Override
	public List<PlanetDto> getAllPlanets() {
		ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "/planets", String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode root;
		try {
			root = objectMapper.readTree(response.getBody());
			JsonNode results = root.path("results");

			return objectMapper.readValue(results.toString(), new TypeReference<List<PlanetDto>>() {
			});
		} catch (IOException e) {
			LOGGER.error("Exception",e);
		}
		return new ArrayList<PlanetDto>();

	}

}
