package com.ventura013.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.stereotype.Service;

import com.ventura013.data.vo.v1.PersonVO;
import com.ventura013.domain.Person;
import com.ventura013.mapper.DozerMapper;
import com.ventura013.repositories.PersonRepository;
import com.ventura013.resources.PersonResource;
import com.ventura013.services.exception.RequiredObjectIsNullException;
import com.ventura013.services.exception.ResourceNotFoundException;

@Service
public class PersonServices {
 
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;

	public List<PersonVO> findAll() {
		logger.info("Finding all people!");
		var persons = DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> p.add(linkTo(methodOn(PersonResource.class).findById(p.getKey())).withSelfRel()));
		return persons;
	}

	public PersonVO findById(Long id) {		
		logger.info("Finding one person!");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		PersonVO vo =  DozerMapper.parseObject(entity, PersonVO.class); 
		vo.add(linkTo(methodOn(PersonResource.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public PersonVO create(PersonVO person) {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		
		logger.info("Creating one person!");		
		var entity = DozerMapper.parseObject(person, Person.class);
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonResource.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one person!");	
		
		var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		PersonVO vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonResource.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Delete one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		repository.delete(entity);
	}
}
