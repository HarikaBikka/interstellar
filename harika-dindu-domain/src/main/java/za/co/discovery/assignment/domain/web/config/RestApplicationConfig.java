/**
 * 
 */
package za.co.discovery.assignment.domain.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Harika
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("za.co.discovery.assignment.domain.web.controller")
public class RestApplicationConfig {

}