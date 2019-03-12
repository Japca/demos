package cz.japca.common.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

import cz.japca.common.entity.UserRegistration;

/**
 * Created by Jakub krhovják on 3/12/19.
 */
@Configuration
@EntityScan(basePackageClasses = {UserRegistration.class})
public class CommonConfiguration {

}
