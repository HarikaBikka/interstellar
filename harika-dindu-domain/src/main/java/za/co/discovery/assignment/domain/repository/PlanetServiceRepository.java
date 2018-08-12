/**
 * 
 */
package za.co.discovery.assignment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import za.co.discovery.assignment.domain.entity.PlanetEntity;

/**
 * @author Harika
 *
 */
@Transactional
public interface PlanetServiceRepository extends JpaRepository<PlanetEntity, String> {

}
