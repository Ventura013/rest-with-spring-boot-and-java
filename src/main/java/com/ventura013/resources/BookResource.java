package com.ventura013.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ventura013.data.vo.v1.BookVO;
import com.ventura013.services.BookServices;
import com.ventura013.util.MediaType;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/api/books/v1")
@Tag(name = "Book", description = "Endpoints for Managing Books")
public class BookResource {

	@Autowired
    BookServices service;
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all Books", description = "Finds all Books", tags = {"Book"},
		responses = {
			@ApiResponse(description = "Sucess", responseCode = "200",
					content = {
							@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = BookVO.class))
									)
							}),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404", content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = {@Content})
			})
	public List<BookVO> findAll() {
		return service.findAll();
	}
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Find a Book", description = "Find a Book", tags = { "Book" }, 
		responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = { @Content }),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content }),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content }),
			@ApiResponse(description = "Not Found", responseCode = "404", content = { @Content }),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = { @Content }) 
			})
	public BookVO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
				 produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a new Book",
		description = "Adds a new Book by passing in a JSON, XML or YML representation of Book!", tags = { "Book" },
		responses = {
			@ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content }),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content }),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = { @Content }) 
			})
	public BookVO create(@RequestBody BookVO book) {
		return service.create(book);
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
				produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Updates a Book",
		description = "Updates a new Book by passing in a JSON, XML or YML representation of Book!", tags = { "Book" },
		responses = {
			@ApiResponse(description = "Update", responseCode = "200", content = @Content(schema = @Schema(implementation = BookVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content }),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content }),
			@ApiResponse(description = "Not Found", responseCode = "404", content = { @Content }),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = { @Content }) 
		})
	public BookVO update(@RequestBody BookVO book) {
		return service.update(book);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deletes a Book",
		description = "Deletes a Book by passing in a JSON, XML or YML representation of Person!", tags = { "Book" },
		responses = {
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = { @Content }),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = { @Content }),
			@ApiResponse(description = "Not Found", responseCode = "404", content = { @Content }),
			@ApiResponse(description = "Internal Error", responseCode = "500", content = { @Content }) 
	})
	public ResponseEntity<?> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
