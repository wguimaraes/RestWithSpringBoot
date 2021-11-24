package br.com.wguimaraes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wguimaraes.converter.DozerConverter;
import br.com.wguimaraes.converter.custom.BooksConverter;
import br.com.wguimaraes.data.model.Books;
import br.com.wguimaraes.data.vo.BooksVO;
import br.com.wguimaraes.exception.ResourceNotFoundException;
import br.com.wguimaraes.repository.BooksRepository;

@Service
public class BooksService {
	
	@Autowired
	BooksRepository repository;
	
	@Autowired
	BooksConverter converter;
	
	public BooksVO create(BooksVO books) {
		Books entity = DozerConverter.parseObject(books, Books.class);
		BooksVO vo = DozerConverter.parseObject(repository.save(entity), BooksVO.class);
		return vo;
	}
	
	public List<BooksVO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), BooksVO.class);
	}
	
	public BooksVO findById(Long id) {
		Books entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		return DozerConverter.parseObject(entity, BooksVO.class);
				
	}
	
	public BooksVO update(BooksVO books) {
		Books entity = repository.findById(books.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		entity.setTitle(books.getTitle());
		entity.setAuthor(books.getAuthor());
		entity.setPrice(books.getPrice());
		entity.setLaunchDate(books.getLaunchDate());
		BooksVO vo = DozerConverter.parseObject(repository.save(entity), BooksVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Books entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		repository.delete(entity);
	}
	
}
