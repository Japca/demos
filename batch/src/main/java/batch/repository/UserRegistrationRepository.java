package batch.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cz.japca.common.entity.UserRegistration;

/**
 * Created by Jakub krhovják on 3/7/19.
 */
public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Long> {

}
