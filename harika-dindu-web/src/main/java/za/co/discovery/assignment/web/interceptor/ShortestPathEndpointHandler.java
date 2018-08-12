package za.co.discovery.assignment.web.interceptor;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.WebServiceClientException;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.context.MessageContext;

/**
 * 
 */

/**
 * @author Harika
 *
 */
@Component
public class ShortestPathEndpointHandler implements ClientInterceptor {
	private static final Logger LOGGER = Logger.getLogger(ShortestPathEndpointHandler.class);

	@Override
	public boolean handleRequest(MessageContext messageContext) throws WebServiceClientException {
		HttpLoggingUtils.logMessage("Client Request Message", messageContext.getRequest());
		return true;
	}

	@Override
	public boolean handleResponse(MessageContext messageContext) throws WebServiceClientException {
		HttpLoggingUtils.logMessage("Client Response Message", messageContext.getResponse());
		return true;
	}

	@Override
	public boolean handleFault(MessageContext messageContext) throws WebServiceClientException {
		LOGGER.info("Endpoint Exception Handling");
		return true;
	}

	@Override
	public void afterCompletion(MessageContext messageContext, Exception ex) throws WebServiceClientException {
		LOGGER.info("Execute code after completion");

	}
}
