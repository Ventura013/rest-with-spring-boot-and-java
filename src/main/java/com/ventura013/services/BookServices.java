package com.ventura013.services;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ventura013.data.vo.v1.BookVO;
import com.ventura013.domain.Book;
import com.ventura013.mapper.DozerMapper;
import com.ventura013.repositories.BookRepository;
import com.ventura013.resources.BookResource;
import com.ventura013.services.exception.RequiredObjectIsNullException;
import com.ventura013.services.exception.ResourceNotFoundException;

@Service
public class BookServices {
 
	private Logger logger = Logger.getLogger(BookServices.class.getName());
	
	@Autowired
	BookRepository repository;

	public List<BookVO> findAll() {
		logger.info("Finding all Book!");
		var books = DozerMapper.parseListObjects(repository.findAll(), BookVO.class);
		books.stream().forEach(p -> p.add(linkTo(methodOn(BookResource.class).findById(p.getKey())).withSelfRel()));
		return books;
	}

	public BookVO findById(Long id) {		
		logger.info("Finding one book!");
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		BookVO vo =  DozerMapper.parseObject(entity, BookVO.class); 
		vo.add(linkTo(methodOn(BookResource.class).findById(id)).withSelfRel());
		return vo;
	}
	
	public BookVO create(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		
		logger.info("Creating one book!");		
		var entity = DozerMapper.parseObject(book, Book.class);
		BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookResource.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public BookVO update(BookVO book) {
		
		if(book == null) throw new RequiredObjectIsNullException();
		
		logger.info("Updating one book!");	
		
		var entity = repository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		entity.setAuthor(book.getAuthor());
		entity.setLaunchDate(book.getLaunchDate());
		entity.setPrice(book.getPrice());
		entity.setTitle(book.getTitle());
		BookVO vo = DozerMapper.parseObject(repository.save(entity), BookVO.class);
		vo.add(linkTo(methodOn(BookResource.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Delete one book!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found fot this ID!"));
		repository.delete(entity);
	}
}
