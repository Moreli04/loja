package com.moreli.loja.config;

import org.hibernate.tool.schema.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.moreli.loja.service.DBService;
import com.moreli.loja.service.EmailService;
import com.moreli.loja.service.impl.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {

	@Autowired
	private DBService dBService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Bean
	public boolean instantiateDatabase() {
		if(!Action.CREATE.name().equalsIgnoreCase(strategy)) {
			return false;
		}
		dBService.instantiateTestDatabase();
		return true;
	}
	
	@Bean 
	public EmailService emailService() {
		return new SmtpEmailService();
	}
}
