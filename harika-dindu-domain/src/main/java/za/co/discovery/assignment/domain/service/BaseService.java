/**
 * 
 */
package za.co.discovery.assignment.domain.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import za.co.discovery.assignment.domain.service.exception.GenericServiceException;

/**
 * @author Harika
 *
 */
public interface BaseService<T, ID extends Serializable> {
	
	@Transactional
	public default List<T> findAll() {
		return (List<T>)getRepository().findAll();
	}

	@Transactional
	public default T get(ID id) {
		return getRepository().findOne(id);
	}

	@Transactional
	public default T save(T entity) {
		return getRepository().save(entity);
	}

	@Transactional
	public default void delete(ID id) {
		if (getRepository().exists(id)) {
			getRepository().delete(id);
		} else {
			throw new GenericServiceException(">> 'id' doesn't exists: " + id);
		}
	}

	@Transactional
	public default void update(T entity) {
		if (getRepository().exists(getId(entity))) {
			getRepository().save(entity);
		} else {
			throw new GenericServiceException("Can't update  because it doesn't exist in DB: " + entity);
		}
	}

	public ID getId(T entity);

	public JpaRepository<T, ID> getRepository();
}
