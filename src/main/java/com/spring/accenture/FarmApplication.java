package com.spring.accenture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class FarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(FarmApplication.class, args);
		
		/*ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		FarmCreature aNewChicken = context.getBean("chick", FarmCreature.class);
		
		
		System.out.println(aNewChicken.feed());
		
		
		context.close();*/
		
	}

}
