/**
 * 
 */
package za.co.discovery.assignment.domain.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import za.co.discovery.assignment.domain.entity.PlanetRoutesId;
import za.co.discovery.assignment.domain.entity.RouteEntity;
import za.co.discovery.assignment.domain.service.BaseService;
import za.co.discovery.assignment.domain.web.vo.ResponseVO;

/**
 * @author Harika
 *
 */
@RestController
public class RouteController {

	@Autowired
	BaseService<RouteEntity, PlanetRoutesId> routeService;

	@GetMapping("/rou")
	public String home() {
		return "Welocme to route controller!!!";
	}

	@GetMapping("/routes")
	public ResponseEntity<ResponseVO<List<RouteEntity>>> allRoutes() {
		return ResponseEntity.ok(new ResponseVO<>(routeService.findAll()));
	}

	@PostMapping("/route")
	public ResponseEntity<ResponseVO<RouteEntity>> createRoute(@RequestBody RouteEntity routeEntity) {
		return ResponseEntity.ok(new ResponseVO<>(routeService.save(routeEntity)));
	}

	@GetMapping("/route/{routeId}")
	public ResponseEntity<ResponseVO<RouteEntity>> getRoute(@PathVariable PlanetRoutesId planetRoutesId) {
		return ResponseEntity.ok(new ResponseVO<>(routeService.get(planetRoutesId)));
	}

	@PutMapping("/route/{routeId}")
	public ResponseEntity<RouteEntity> updateRoute(@RequestBody RouteEntity routeEntity) {
		routeService.update(routeEntity);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/route/{routeId}")
	public ResponseEntity<ResponseVO<String>> deleteRoute(@PathVariable PlanetRoutesId planetRoutesId) {
		routeService.delete(planetRoutesId);
		return ResponseEntity.ok(new ResponseVO<>("Deleted route"));
	}
}