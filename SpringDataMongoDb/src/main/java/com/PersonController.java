package com;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping(path="/hello")
	public String sayHello() {
		Person person=new Person("1","Nikhil",1000);
		personRepository.save(person);
	
		return "Hello!";
	}
	
	@RequestMapping(path="/find/{id}")
	public String find(@PathVariable("id")String id) {
	
		Optional<Person> optPerson=personRepository.findById(id);
		if(optPerson.isPresent())
			return optPerson.get().toString();
		return "Something went wrong";
	}
	
	@RequestMapping(path="/update")
	public String update() {
		Person person=personRepository.findById("1").get();
		person.setAge(28);
		personRepository.save(person);
		return "updated:";
	}
}
