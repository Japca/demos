package cz.japca.rabbit.messaging;

import cz.japca.common.entity.UserRegistration;

public interface UserRegistrationMessagingService {

  void sendOrder(UserRegistration order);
  
}
