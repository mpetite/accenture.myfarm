package com.spring.accenture;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.spring.accenture")
@ComponentScan("com.spring.accenture.entities")
@ComponentScan("com.spring.accenture.controllers")
@ComponentScan("com.spring.accenture.service")
@ComponentScan("com.spring.accenture.repositories")
public class SpringConfig {

}
