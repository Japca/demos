package batch.rabbit;

import cz.japca.common.entity.UserRegistration;

public interface UserRegistrationReceiver {

  UserRegistration receiveOrder();

}