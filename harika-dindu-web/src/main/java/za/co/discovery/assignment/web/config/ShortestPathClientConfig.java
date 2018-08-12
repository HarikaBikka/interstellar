/**
 * 
 */
package za.co.discovery.assignment.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;

import za.co.discovery.assignment.web.interceptor.ShortestPathEndpointHandler;

/**
 * @author Harika
 *
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ShortestPathClientConfig {
	
	@Value("${defaulturi}")
	private String defaulturi;

	@Bean
	Jaxb2Marshaller jaxb2Marshaller() {
		Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
		jaxb2Marshaller.setContextPath("za.co.discovery.assignment.ws.client");

		return jaxb2Marshaller;
	}

	@Bean
	public WebServiceTemplate webServiceTemplate() {
		WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
		webServiceTemplate.setMarshaller(jaxb2Marshaller());
		webServiceTemplate.setUnmarshaller(jaxb2Marshaller());
		webServiceTemplate.setDefaultUri(defaulturi+"/shortestpathws/shortestroute.wsdl");

		// register the LogHttpHeaderClientInterceptor
	    ClientInterceptor[] interceptors =
	        new ClientInterceptor[] {new ShortestPathEndpointHandler()};
	    webServiceTemplate.setInterceptors(interceptors);

		return webServiceTemplate;
	}

}
