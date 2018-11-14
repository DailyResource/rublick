package online.rubick.applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ImportResource(locations={"classpath:mykaptcha.xml"}) 
@EnableAutoConfiguration
@ComponentScan("online.rubick.applications")
@EnableScheduling
@EnableCaching
public class RubickApplication {
	public static void main(String[] args) {
		SpringApplication.run(RubickApplication.class, args);
	}
}
