import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import za.co.discovery.assignment.domain.service.impl.PlanetService;

@Configuration
@ComponentScan(basePackages={"za.co.discovery.*"})
public class AppConfig {
    @Bean
    public PlanetService getSampleService() {
        return new PlanetService();
    }
}
