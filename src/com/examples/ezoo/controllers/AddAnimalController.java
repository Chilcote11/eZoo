package com.examples.ezoo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.examples.ezoo.model.Animal;

@Controller
public class AddAnimalController {
	
	@RequestMapping(value="/addAnimal", method=RequestMethod.GET)
	public String addAnimal(Model model) {
		model.addAttribute("newAnimal", new Animal());
		return "addAnimal";		
	}
}
