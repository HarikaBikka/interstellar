/**
 * 
 */
package za.co.discovery.assignment.domain.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import za.co.discovery.assignment.domain.entity.PlanetRoutesId;
import za.co.discovery.assignment.domain.entity.RouteEntity;

/**
 * @author Harika
 *
 */
@Transactional
public interface RouteServiceRepository extends JpaRepository<RouteEntity, PlanetRoutesId> {

}
