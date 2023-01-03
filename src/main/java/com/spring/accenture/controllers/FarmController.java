package com.spring.accenture.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.accenture.entities.Chicken;
import com.spring.accenture.entities.Farm;
import com.spring.accenture.entities.Status;
import com.spring.accenture.repositories.StatusRepository;
import com.spring.accenture.service.ChickenService;
import com.spring.accenture.service.FarmService;



@Controller
@RequestMapping("")
public class FarmController {
	
	@Autowired
	private FarmService farmService;
	
	@Autowired
	private ChickenService chickenService;
	
	@Autowired
	private StatusRepository statusRepository;

	//mapeo la pagina de bienvenida, donde el usuario selecciona la granja que desea manejar
	//HTML: home
    @GetMapping(value="/")
    public String homePage(Model model) {
    	
    	model.addAttribute("farmList", statusRepository.findAll());
    	
        return "home";
    }
    
    //mapeo el menu principal de la granja seleccionada por el usuario, donde puede ver su billetera, acceder a sus datos y al servicio del mercado
    //HTML:farm
    @GetMapping(value="/farm")
    public String farmView(@RequestParam String farmID, Model model) {
    	
    	     
        int nbrFarmID = Integer.valueOf(farmID);
        
        Farm theFarm = farmService.getFarmByID(nbrFarmID);
		
		List<Chicken> farmChickenList = chickenService.findProducts(nbrFarmID,0);
		
		Status farmStatus =theFarm.getStatus();
		
        model.addAttribute("farmStatus", farmStatus);
        model.addAttribute("chickenCount", chickenService.findProducts(nbrFarmID,1).size());
        model.addAttribute("eggCount", chickenService.findProducts(nbrFarmID,2).size());
        model.addAttribute("chickenList", farmChickenList);
            
    		
    	return "farm";
    	}
        
}

