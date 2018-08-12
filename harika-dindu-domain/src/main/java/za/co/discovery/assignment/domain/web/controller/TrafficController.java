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
import za.co.discovery.assignment.domain.entity.TrafficEntity;
import za.co.discovery.assignment.domain.service.BaseService;
import za.co.discovery.assignment.domain.web.vo.ResponseVO;

/**
 * @author Harika
 *
 */
@RestController
public class TrafficController {

	@Autowired
	BaseService<TrafficEntity, PlanetRoutesId> trafficService;

	@GetMapping("/tra")
	public String home() {
		return "Traffic  controller!!!";
	}

	@GetMapping("/traffics")
	public ResponseEntity<ResponseVO<List<TrafficEntity>>> allPlanets() {
		return ResponseEntity.ok(new ResponseVO<>(trafficService.findAll()));
	}
	@PostMapping("/traffic")
	public ResponseEntity<ResponseVO<TrafficEntity>> createPlanet(@RequestBody TrafficEntity planetData) {
		return ResponseEntity.ok(new ResponseVO<>(trafficService.save(planetData)));
	}

	@GetMapping("/traffic/{trafficId}")
	public ResponseEntity<ResponseVO<TrafficEntity>> getTrafic(@PathVariable PlanetRoutesId planetRoutesId) {
		return ResponseEntity.ok(new ResponseVO<>(trafficService.get(planetRoutesId)));
	}

	@PutMapping("/traffic/{trafficId}")
	public ResponseEntity<TrafficEntity> updateTraffic(@RequestBody TrafficEntity planet) {
		trafficService.update(planet);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/traffic/{trafficId}")
	public ResponseEntity<ResponseVO<String>> deleteTraffic(@PathVariable PlanetRoutesId planetRoutesId) {
		return ResponseEntity.ok(new ResponseVO<>("Traffic route"));
	}
}