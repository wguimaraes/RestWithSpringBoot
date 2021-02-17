package br.com.wguimaraes.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import br.com.wguimaraes.model.Person;

@Service
public class PersonService {

	private final AtomicLong counter = new AtomicLong();
	
	public Person create(Person person) {
		return person;
	}
	
	public Person update(Person person) {
		return person;
	}
	
	public void delete(String id) {
		
	}
	
	public List<Person> findAll(){
		return mockPersons();
	}
	
	public Person findById(String id) {
		Person person = new Person();
		person.setId(counter.incrementAndGet());
		person.setFirstName("William");
		person.setLastName("Guimarães");
		person.setAddress("Porto Alegre, RS, Brasil");
		person.setGender("M");
		return person;
	}

	private List<Person> mockPersons() {
		List<Person> persons = new ArrayList<Person>();
		for (int i = 0; i < 8; i++) {
			Person person = new Person();
			person.setId(counter.incrementAndGet());
			person.setFirstName("Name " + i);
			person.setLastName("Last Name" + i);
			person.setAddress("Address " + i);
			person.setGender((i % 2 == 0 ? "M" : "F"));
			persons.add(person);
		}
		
		return persons;
	}
	
}
