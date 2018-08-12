package za.co.discovery.assignment.service.ws.interceptor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ws.context.MessageContext;
import org.springframework.ws.server.EndpointInterceptor;

/**
 * 
 */

/**
 * @author Harika
 *
 */
@Component
public class ShortestPathEndpointHandler implements EndpointInterceptor {
	private static final Logger LOGGER = Logger.getLogger(ShortestPathEndpointHandler.class);

	public boolean handleRequest(MessageContext messageContext, Object endpoint) throws Exception {
		HttpLoggingUtils.logMessage("Server Request Message", messageContext.getRequest());
		return true;
	}

	public boolean handleResponse(MessageContext messageContext, Object endpoint) throws Exception {
		HttpLoggingUtils.logMessage("Server Response Message", messageContext.getResponse());
		return true;
	}

	public boolean handleFault(MessageContext messageContext, Object endpoint) throws Exception {
		LOGGER.info("Endpoint Exception Handling");
		return true;
	}

	public void afterCompletion(MessageContext messageContext, Object endpoint, Exception ex) throws Exception {
		LOGGER.info("Execute code after completion");
	}
}
