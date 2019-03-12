package batch.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import cz.japca.common.entity.UserRegistration;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRegistrationListener {
  

  @RabbitListener(queues = "tacocloud.order.queue")
  public void receiveOrder(UserRegistration registration) {
    log.info("Registration received: {}", registration);
  }
  
}
