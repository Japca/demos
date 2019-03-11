package cz.japca.rabbit.messaging;

import cz.japca.common.entity.UserRegistration;

public interface OrderMessagingService {

  void sendOrder(UserRegistration order);
  
}
