package com.home.estudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
@EntityScan(basePackages = {"com.home.estudy.entity"}) 
@ComponentScan("com.home.estudy")
public class EStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStudyApplication.class, args);
	}
}
