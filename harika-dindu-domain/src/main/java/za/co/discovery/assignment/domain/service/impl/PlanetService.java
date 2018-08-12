/**
 * 
 */
package za.co.discovery.assignment.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.discovery.assignment.domain.entity.PlanetEntity;
import za.co.discovery.assignment.domain.repository.PlanetServiceRepository;
import za.co.discovery.assignment.domain.service.BaseService;

/**
 * @author Harika
 *
 */
@Service
@Transactional
public class PlanetService implements BaseService<PlanetEntity, String> {
	
	@Autowired
	PlanetServiceRepository planetServiceRepository;


	@Override
	public String getId(PlanetEntity entity) {
		return entity.getPlanetNode();
	}

	@Override
	public JpaRepository<PlanetEntity, String> getRepository() {
		return this.planetServiceRepository;
	}

}
