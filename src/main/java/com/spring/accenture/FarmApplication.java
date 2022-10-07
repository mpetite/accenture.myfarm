package com.spring.accenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.spring.accenture.entities.FarmCreature;

@SpringBootApplication
public class FarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmApplication.class, args);
		
		/*AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		FarmCreature aNewChicken = context.getBean("chicken", FarmCreature.class);
		
		
		System.out.println(aNewChicken.feed());
		
		
		context.close();*/
		
	}

}
