package com.bernacki.abbank.user_service;

import com.bernacki.abbank.user_service.entity.IndividualUser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
@EnableFeignClients
public class UserServiceApplication {

	@Bean
	@Scope("prototype")
	public IndividualUser user(){
		return new IndividualUser();
	}

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
