package online.rubick.applications;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 今创EAM系统入口程序
 * 
 * @author 张峻峰
 * @Date 2018年10月31日
 */
@SpringBootApplication
@ImportResource(locations = { "classpath:mykaptcha.xml" })
@EnableAutoConfiguration
@ComponentScan("com.letsiot.applications,com.letsiot.eam")
@EnableScheduling
@EnableCaching
public class RubickApplication {

	public static void main(String[] args) {
		SpringApplication.run(RubickApplication.class, args);
	}

}
