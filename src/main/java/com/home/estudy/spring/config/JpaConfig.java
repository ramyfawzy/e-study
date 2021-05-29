package com.home.estudy.spring.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EntityScan("com.home.estudy.entity")
@ComponentScan("com.home.estudy")
@Configuration
public class JpaConfig {

}
