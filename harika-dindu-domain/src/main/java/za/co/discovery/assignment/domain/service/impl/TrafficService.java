/**
 * 
 */
package za.co.discovery.assignment.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.discovery.assignment.domain.entity.PlanetRoutesId;
import za.co.discovery.assignment.domain.entity.TrafficEntity;
import za.co.discovery.assignment.domain.repository.TrafficServiceRepository;
import za.co.discovery.assignment.domain.service.BaseService;

/**
 * @author Harika
 *
 */
@Service
@Transactional
public class TrafficService implements BaseService<TrafficEntity, PlanetRoutesId> {

	@Autowired
	TrafficServiceRepository trafficServiceRepository;

	
	@Override
	public PlanetRoutesId getId(TrafficEntity entity) {
		return entity.getPlanetRoute();
	}

	@Override
	public JpaRepository<TrafficEntity, PlanetRoutesId> getRepository() {
		return this.trafficServiceRepository;
	}

}
