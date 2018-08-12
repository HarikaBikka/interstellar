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

import za.co.discovery.assignment.domain.entity.PlanetEntity;
import za.co.discovery.assignment.domain.service.BaseService;
import za.co.discovery.assignment.domain.web.vo.ResponseVO;

/**
 * @author Harika
 *
 */
@RestController
public class PlanetController {

	@Autowired
	BaseService<PlanetEntity, String> planetService;

	@GetMapping("/pla")
	public String home() {
		return "Planet route application!!!";
	}

	@GetMapping("/planets")
	public ResponseEntity<ResponseVO<List<PlanetEntity>>> allPlanets() {
		return ResponseEntity.ok(new ResponseVO<>(planetService.findAll()));
	}

	@PostMapping("/planet")
	public ResponseEntity<ResponseVO<PlanetEntity>> createPlanet(@RequestBody PlanetEntity planetData) {
		return ResponseEntity.ok(new ResponseVO<>(planetService.save(planetData)));
	}

	@GetMapping("/planet/{planetId}")
	public ResponseEntity<ResponseVO<PlanetEntity>> getPlanet(@PathVariable String planetId) {
		return ResponseEntity.ok(new ResponseVO<>(planetService.get(planetId)));
	}

	@PutMapping("/planet/{planetId}")
	public ResponseEntity<PlanetEntity> updatePlanet(@RequestBody PlanetEntity planet) {
		planetService.update(planet);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/planet/{planetId}")
	public ResponseEntity<ResponseVO<String>> deletePlanet(@PathVariable String planetId) {
		planetService.delete(planetId);
		return ResponseEntity.ok(new ResponseVO<>(planetId));
	}
}