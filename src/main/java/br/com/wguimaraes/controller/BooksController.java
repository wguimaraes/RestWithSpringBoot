package br.com.wguimaraes.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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

import br.com.wguimaraes.data.vo.BooksVO;
import br.com.wguimaraes.services.BooksService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "Books endpoint", description = "Description of books endpoint", tags = {"BooksEndpoint", "RESTFul"})
@RestController
@RequestMapping("/books")
public class BooksController {
	
	@Autowired
	private BooksService service;
	
	@ApiOperation(value = "Create a book in the system")
	@PostMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BooksVO create(@RequestBody BooksVO books) {
		BooksVO booksVO = service.create(books);
		booksVO.add(linkTo(methodOn(BooksController.class).findById(booksVO.getKey())).withSelfRel());
		return booksVO;
	}
	
	@ApiOperation(value = "Alter book data in the system by passing id key and the new values")
	@PutMapping(produces = { "application/json", "application/xml", "application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public BooksVO update(@RequestBody BooksVO books) {
		BooksVO booksVO = service.update(books);
		booksVO.add(linkTo(methodOn(BooksController.class).findById(booksVO.getKey())).withSelfRel());
		return booksVO;
	}
	
	@ApiOperation(value = "Remove a book from system by passing id key of record to remove")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
	
	@ApiOperation(value = "Find all books in the system")
	@GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
	public List<BooksVO> findAll() {
		List<BooksVO> bookss = service.findAll();
		bookss
			.stream()
				.forEach(p -> p.add(
						linkTo(methodOn(BooksController.class).findById(p.getKey())).withSelfRel()
					)
				);
		return bookss;
	}
	
	@ApiOperation(value = "Find a book by id key")
	@GetMapping(value = "/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
	public BooksVO findById(@PathVariable("id") Long id) {
		BooksVO booksVO = service.findById(id);
		booksVO.add(linkTo(methodOn(BooksController.class).findById(id)).withSelfRel());
		return booksVO;
	}
 
}
