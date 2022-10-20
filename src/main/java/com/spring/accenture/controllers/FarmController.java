package com.spring.accenture.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.spring.accenture.entities.Farm;
import com.spring.accenture.service.FarmService;

@Controller
public class FarmController {
	
	
	
	
	@Autowired
	private FarmService farmService;
	//mapeo la pagina de bienvenida, donde se logeara el usuario a su cuenta
	//HTML: home
    @GetMapping("/")
    public String homePage(Model model) {
    		
    	
        
        return "home";
    }
    
    //mapeo el menu principal de la cuenta del usuario, donde puede ver su billetera y acceder a su granja y al mercado
    //HTML:welcomeMenu
    @GetMapping("/{userName}")
    public ModelAndView welcomeMenu(@PathVariable String userName, @PathVariable String userPassword) {
    	//Falta:armar la logica para que los datos que aparezacn sean dependientes de la cuenta del usuario
    	     
        ModelAndView mav = new ModelAndView();    
            
    	
    	//si los campos no estan vacios, cargo el menu ppal
    	if(userName.length()>0 && userPassword.length()>0) {
    		
    		mav.setViewName("welcomeMenu");
    		mav.addObject(userName, "name");
    		
    		
    		return mav;
    		
    	}
    	
    	//sino, le muestro un gentil mensaje y lo dejo en home
    	else {
    		mav.setViewName("home.jsp");
    		mav.addObject("Your credentials are wrong","msjErrorCredenciales");
    		
    		
    		return mav;
    		
    	}
    }
    
    
    
    //mapeo el mercado, donde el usuario puede comprar/vender gallinas, huevos y vacas
    //HTML: market
    @GetMapping("/market")
    public String market(Model model) {
    	//falta: Todo
    	return "market";
    }
    
    
    //mapeo la granja, donde el usuario va a poder ver el status de su producto, 
    //HTML:farm
    @GetMapping("/{farmID}")
    public Farm farm(@PathVariable long farmID) {

    	return farmService.getFarmByID(farmID);
    }
}
