package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;

/**
 * Main interface used to execute CRUD methods on Animal class
 * 
 * @author anon - someone at Revature
 * 		+ This was part of the original template
 * @author Cory Chilcote
 * 		+ Added the delete animal method
 *
 */
public interface AnimalDAO {

	/**
	 * Used to retrieve a list of all Animals 
	 * @return
	 */
	List<Animal> getAllAnimals();

	/**
	 * Used to persist the animal to the datastore
	 * @param animalToSave
	 */
	void saveAnimal(Animal animalToSave) throws Exception;
	
	/**
	 * Used to delete an animal from the datastore
	 * 
	 * @param animalToDelete
	 */
	void deleteAnimal(Animal animalToDelete) throws Exception;

	
}
