package chw.intern.nts.reservation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages = {"chw.intern.nts.reservation.dao", "chw.intern.nts.reservation.service.impl"})
@Import({DBConfig.class})
public class ApplicationConfig {
	
}
