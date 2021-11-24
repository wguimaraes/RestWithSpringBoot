package br.com.wguimaraes.converter.custom;

import org.springframework.stereotype.Service;

import br.com.wguimaraes.data.model.Books;
import br.com.wguimaraes.data.vo.BooksVO;

@Service
public class BooksConverter {
	
	public BooksVO convertEntityToVO(Books books) {
		BooksVO vo = new BooksVO();
		vo.setKey(books.getId());
		vo.setTitle(books.getTitle());
		vo.setAuthor(books.getAuthor());
		vo.setPrice(books.getPrice());
		vo.setLaunchDate(books.getLaunchDate());
		return vo;
	}	
	
	public Books convertVOToEntity(BooksVO books) {
		Books entity = new Books();
		entity.setId(books.getKey());
		entity.setTitle(books.getTitle());
		entity.setAuthor(books.getAuthor());
		entity.setPrice(books.getPrice());
		entity.setLaunchDate(books.getLaunchDate());
		return entity;
	}

}
