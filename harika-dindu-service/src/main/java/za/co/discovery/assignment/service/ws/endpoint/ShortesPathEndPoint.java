/**
 * 
 */
package za.co.discovery.assignment.service.ws.endpoint;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import za.co.discovery.assignment.service.ShortestPathService;
import za.co.discovery.assignment.service.algorithm.dto.PlanetDto;
import za.co.discovery.assignment.ws.service.ShortestPathRequest;
import za.co.discovery.assignment.ws.service.ShortestPathResponse;

/**
 * @author Harika
 *
 */
@Endpoint
public class ShortesPathEndPoint {

	@Autowired
	private ShortestPathService shortestPathService;

	/**
	 * Namespace of both request and response.
	 */
	public static final String NAMESPACE_URI = "http://assignment.discovery.co.za/ShortestPathService/schema";

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "shortestPathRequest")
	@ResponsePayload
	public ShortestPathResponse getShortestPath(@RequestPayload ShortestPathRequest request) throws IOException {

		return shortestPathService.getShortestPath(new PlanetDto(request.getDestPlanet()),
				new PlanetDto(request.getOriginPlanet()), request.isTraffic());
	}

}
