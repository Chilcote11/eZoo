package com.examples.ezoo.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.model.Animal;

public class TestAnimalDAO {
	
	public static void main(String[] args) {
//		AnimalDAO dao = new AnimalDAOImpl(); // this won't work.. need application context
		ApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
		AnimalDAO dao = (AnimalDAO) context.getBean(AnimalDAO.class);
		
		// test saveAnimal method
		Animal animalToSave = new Animal(
				400,
				"jack",
				"dog", 
				"dog", 
				"dog", 
				"dog", 
				"dog", 
				"dog", 
				"dog", 
				1D,
				1D,
				"dog",
				"Healthy",
				1
				);
		try {
			dao.saveAnimal(animalToSave);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
//		 test getAllAnimals method
		List<Animal> animals = dao.getAllAnimals();
		Collections.sort(animals);
		System.out.println("Animals printed below:");
		for (Animal a : animals) {
			System.out.println(a);
		}
	}
}
