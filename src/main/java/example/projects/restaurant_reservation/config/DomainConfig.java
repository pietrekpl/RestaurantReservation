package example.projects.restaurant_reservation.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("example.projects.restaurant_reservation.domain")
@EnableJpaRepositories("example.projects.restaurant_reservation.repos")
@EnableTransactionManagement
public class DomainConfig {
}
