/**
 * 
 */
package za.co.discovery.assignment.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Harika
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"za.co.discovery.assignment.*"})
@Import(value = { WebMvcConfig.class, ShortestPathClientConfig.class })
public class WebApplicationConfig {

}