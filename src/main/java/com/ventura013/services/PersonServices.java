package com.ventura013.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura013.domain.Person;
import com.ventura013.repositories.PersonRepository;
import com.ventura013.services.exception.ResourceNotFoundException;

@Service
public class PersonServices {
 
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;

	public List<Person> findAll() {
		logger.info("Finding all people");
		return repository.findAll();
	}

	public Person findById(Long id) {		
		logger.info("Finding one person");
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
	}
	
	public Person create(Person person) {
		logger.info("Creating one person");		
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating one person");	
		
		var entity = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		return repository.save(person);
	}
	
	public void delete(Long id) {
		logger.info("Delete one person");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		repository.delete(entity);
	}
}
