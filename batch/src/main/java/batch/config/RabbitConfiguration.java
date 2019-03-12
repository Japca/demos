package batch.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import batch.rabbit.UserRegistrationListener;

/**
 * Created by Jakub krhovják on 3/12/19.
 */
@Configuration
public class RabbitConfiguration {


    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public UserRegistrationListener userRegistrationListener() {
        return new UserRegistrationListener();
    }

}
