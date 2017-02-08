package se.jjek;

import java.security.Provider.Service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.SystemEnvironmentPropertySource;

import se.jjek.model.Issue;
import se.jjek.model.Team;
import se.jjek.model.User;
import se.jjek.model.WorkItem;
import se.jjek.service.EntityService;

@SpringBootApplication
public class SpringJpaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaProjectApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner run(ApplicationContext context){
		return args -> {
			
			EntityService entityService = context.getBean(EntityService.class);
			
			// blev så mycket kod, men har provat alla metoder och inte fått några felkoder.
			

		};
	}
}
