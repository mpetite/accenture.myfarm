package com.spring.accenture.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FarmController {
    /*@Value("${spring.application.name}")
    String appName;*/

	
	//mapeo la pagina de bienvenida, donde se logeara el usuario a su cuenta
	//agregar funcion de creación de usuario?
	//HTML: home
    @GetMapping("/")
    public String homePage(Model model) {
        //model.addAttribute("appName", appName);
    	//falta: agregar al modelo la logica para el inicio de seción del usuario
    	//Pide Usuario y pass
    	//Si son correctos, dirige al welcome menu
    	
    	
        
        return "home";
    }
    
    //mapeo el menu principal de la cuenta del usuario, donde puede ver su billetera y acceder a su granja y al mercado
    //hacer billetera con varias monedas?
    //HTML:welcomeMenu
    @GetMapping("/welcomeMenu")
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
    @GetMapping("/farm")
    public String farm(Model model) {
    	//falta: Todo	
    	return "farm";
    }
}
