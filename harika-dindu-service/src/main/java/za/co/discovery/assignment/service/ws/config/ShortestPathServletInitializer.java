/**
 * 
 */
package za.co.discovery.assignment.service.ws.config;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

/**
 * @author Harika
 *
 */
public class ShortestPathServletInitializer extends AbstractAnnotationConfigMessageDispatcherServletInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.ws.transport.http.support.
	 * AbstractAnnotationConfigMessageDispatcherServletInitializer#
	 * getRootConfigClasses()
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.ws.transport.http.support.
	 * AbstractAnnotationConfigMessageDispatcherServletInitializer#
	 * getServletConfigClasses()
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { ShortestPathConfig.class };
	}

	@Override
	public boolean isTransformWsdlLocations() {
		return true;
	}
	
}
