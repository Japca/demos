package batch.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import cz.japca.common.entity.UserRegistration;

@Profile("rabbitmq-template")
@Component("templateOrderReceiver")
public class RabbitOrderReceiver implements UserRegistrationReceiver {

  private RabbitTemplate rabbitTemplate;

  public RabbitOrderReceiver(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }
  
  public UserRegistration receiveOrder() {
    return (UserRegistration) rabbitTemplate.receiveAndConvert("tacocloud.order.queue");
  }
  
}
