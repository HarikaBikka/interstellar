package za.co.discovery.assignment.service.ws.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

import za.co.discovery.assignment.service.ws.interceptor.ShortestPathEndpointHandler;

@EnableWs
@Configuration
@ComponentScan("za.co.discovery.assignment.service")
public class ShortestPathConfig extends WsConfigurerAdapter {

	@Bean
	public SimpleXsdSchema shortestPathSchema() {
		return new SimpleXsdSchema(new ClassPathResource("schemas/shortestpath.xsd"));
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Override
	public void addInterceptors(List<EndpointInterceptor> interceptors) {
		PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
		validatingInterceptor.setXsdSchema(shortestPathSchema());
		validatingInterceptor.setValidateRequest(true);
		validatingInterceptor.setValidateResponse(true);
		interceptors.add(validatingInterceptor);

		interceptors.add(new PayloadLoggingInterceptor());

		interceptors.add(new ShortestPathEndpointHandler());
	}

	@Bean(name = "shortestroute")
	public DefaultWsdl11Definition defaultWsdl11Definition() {
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("ShortestPathPort");
		wsdl11Definition.setLocationUri("/shortestpathws");
		wsdl11Definition.setSchema(shortestPathSchema());
		return wsdl11Definition;
	}

}
