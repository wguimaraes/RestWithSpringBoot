package br.com.wguimaraes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wguimaraes.converter.DozerConverter;
import br.com.wguimaraes.converter.custom.PersonConverter;
import br.com.wguimaraes.data.model.Person;
import br.com.wguimaraes.data.vo.PersonVO;
import br.com.wguimaraes.data.vo.v2.PersonVOV2;
import br.com.wguimaraes.exception.ResourceNotFoundException;
import br.com.wguimaraes.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonConverter converter;
	
	public PersonVO create(PersonVO person) {
		Person entity = DozerConverter.parseObject(person, Person.class);
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		Person entity = converter.convertVOToEntity(person);
		PersonVOV2 vo = converter.convertEntityToVO(repository.save(entity));
		return vo;
	}
	
	public List<PersonVO> findAll(){
		return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO findById(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		return DozerConverter.parseObject(entity, PersonVO.class);
				
	}
	
	public PersonVO update(PersonVO person) {
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAddress(person.getAddress());
		entity.setGender(person.getGender());
		PersonVO vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public void delete(Long id) {
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for this ID"));
		repository.delete(entity);
	}
	
}
