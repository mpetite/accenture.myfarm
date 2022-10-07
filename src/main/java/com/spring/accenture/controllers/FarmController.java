package com.spring.accenture.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    	String money = "Ya pooar!";
    	
        
        return "home";
    }
    
    //mapeo el menu principal de la cuenta del usuario, donde puede ver su billetera y acceder a su granja y al mercado
    //hacer billetera con varias monedas?
    //HTML:welcomeMenu
    @GetMapping("/welcomeMenu")
    public String welcomeMenu(Model model) {
    	//Falta:armar la logica para que los datos que aparezacn sean dependientes de la cuenta del usuario
    	
    	return "welcomeMenu";
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
