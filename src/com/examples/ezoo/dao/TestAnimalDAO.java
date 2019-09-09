package com.examples.ezoo.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.examples.ezoo.config.Config;
import com.examples.ezoo.model.Animal;

/**
 * Test script to verify AnimalDAOImpl methods 
 * 
 * Assumptions:
 * 		+ There is no feeding schedule with ID 400 already existing
 * 
 * This test class makes changes to the database, 
 * 	but resets all values to their original state upon completion
 * 
 * @author Cory Chilcote
 * 
 */
public class TestAnimalDAO {
	
	public static void main(String[] args) {

		AbstractApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
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
		
		// test deleteAnimal method
		try {
			dao.deleteAnimal(animalToSave);
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		System.out.println("Animals printed below after deleting Jack:");
		for (Animal a : dao.getAllAnimals()) {
			System.out.println(a);	// unsorted, but results are correct
		}
		
		context.close();
	}
}
