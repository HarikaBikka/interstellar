/**
 * 
 */
package za.co.discovery.assignment.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import za.co.discovery.assignment.domain.entity.PlanetRoutesId;
import za.co.discovery.assignment.domain.entity.RouteEntity;
import za.co.discovery.assignment.domain.repository.RouteServiceRepository;
import za.co.discovery.assignment.domain.service.BaseService;

/**
 * @author Harika
 *
 */
@Service
@Transactional
public class RouteService implements BaseService<RouteEntity, PlanetRoutesId> {

	@Autowired
	RouteServiceRepository routeRepository;

	
	@Override
	public PlanetRoutesId getId(RouteEntity entity) {
		return entity.getPlanetRoute();
	}

	@Override
	public JpaRepository<RouteEntity, PlanetRoutesId> getRepository() {
		return this.routeRepository;
	}

}
