package com.moreli.loja.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.moreli.loja.service.DBService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService dBService;
	
	@Bean
	public boolean instantiateDatabase() {
		dBService.instantiateTestDatabase();
		return true;
	}
}
