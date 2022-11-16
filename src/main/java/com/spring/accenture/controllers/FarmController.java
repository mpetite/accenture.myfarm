package com.spring.accenture.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.accenture.entities.Farm;
import com.spring.accenture.service.FarmService;


@Controller
@RequestMapping("home")
public class FarmController {
	
	@Autowired
	private FarmService farmService;


	//mapeo la pagina de bienvenida, donde se logeara el usuario a su cuenta
	//HTML: home
    @GetMapping(value="")
    public String homePage(Model model) {
    		
        return "home";
    }
    
    //mapeo el menu principal de la cuenta del usuario, donde puede ver su billetera y acceder a su granja y al mercado
    //HTML:welcomeMenu
    @RequestMapping(value="/farm/{farmID}", method = RequestMethod.POST)
    public String farmView(@PathVariable int farmID, Model model) {
    	//Falta:armar la logica para que los datos que aparezacn sean dependientes de la cuenta del usuario
    	     
        
        
		Farm theFarm = farmService.getFarmByID(farmID);
		
		String farmLocationName = theFarm.getStatus().getLocationID();
		String farmLocationSize = theFarm.getStatus().getSize();
		double farmLocationMoney = theFarm.getStatus().getMoney();
		int farmChickenCount = theFarm.getChickenList(1).size();
		int farmEggCount = theFarm.getChickenList(2).size();
		int farmCattleCount = theFarm.getStatus().getCattleCount();
        
		
		model.addAttribute(farmLocationName);
		model.addAttribute(farmLocationSize);
		model.addAttribute(farmLocationMoney);
		model.addAttribute(farmChickenCount);
		model.addAttribute(farmEggCount);
		model.addAttribute(farmCattleCount);
    		
    	return "farm";
    	}
    
    
    //mapeo la granja, donde el usuario va a poder ver el status de su producto, 
    //HTML:farm
/*    
    //CAMBIAR POR LINKEO AL OTRO CONTROLLER
    @GetMapping("/farm/{farmID}")
    public Farm farm(@PathVariable long farmID) {

    	return farmService.getFarmByID(farmID);
    }*/
}
