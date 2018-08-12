/**
 * 
 */
package za.co.discovery.assignment.service.algorithm;

import java.io.IOException;
import java.util.List;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import za.co.discovery.assignment.service.algorithm.dto.PlanetDto;
import za.co.discovery.assignment.service.algorithm.dto.RouteDto;

/**
 * @author Harika
 *
 */
public class RoutesRestServiceTestCase {

	private static RestTemplate REST_TEMPLATE = new RestTemplate();
	private static final String BASE_URL = "http://localhost:8080/harikaDinduModel/";

	@Test
	public void getRoutesResponse() throws JsonProcessingException, IOException {
		ResponseEntity<String> response = REST_TEMPLATE.getForEntity(BASE_URL + "/routes", String.class);
		Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);

		ObjectMapper mapper = new ObjectMapper();

		JsonNode root = mapper.readTree(response.getBody());

		System.out.println(root);
		JsonNode results = root.path("results");
		Assert.assertNotNull(results);

		List<RouteDto> routes = mapper.readValue(results.toString(), new TypeReference<List<RouteDto>>() {
		});

		Assert.assertNotNull(routes);

		ShortestPathAlgorithm shortestPathAlgorith = new ShortestPathAlgorithm();
		PlanetDto destPlanet = new PlanetDto("C'", "Gliese 832");

		System.out.println(shortestPathAlgorith.execute(new PlanetDto("A", "Earth"), destPlanet, routes, true));

		Queue<PlanetDto> path = shortestPathAlgorith.getPath(destPlanet);

		Assert.assertNotNull(path);
		Assert.assertTrue(path.size() > 0);

		for (PlanetDto planet : path) {
			System.out.println(planet);
		}
	}

}
