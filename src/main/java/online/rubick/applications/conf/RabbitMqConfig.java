package online.rubick.applications.conf;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

	@Bean
	public MessageConverter jacksonConverter() {
		Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
		return converter;
	}
}
