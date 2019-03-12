package cz.japca.rabbit.messaging;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cz.japca.common.entity.UserRegistration;

@Service
public class RabbitUserRegistrationMessagingService implements UserRegistrationMessagingService {

    private RabbitTemplate rabbit;

    @Autowired
    public RabbitUserRegistrationMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    public void sendOrder(UserRegistration userRegistration) {
        rabbit.convertAndSend("tacocloud.order.queue", userRegistration,
                new MessagePostProcessor() {
                    @Override
                    public Message postProcessMessage(Message message) throws AmqpException {
                        MessageProperties props = message.getMessageProperties();
                        props.setHeader("X_ORDER_SOURCE", "WEB");
                        return message;
                    }
                });
    }

}
