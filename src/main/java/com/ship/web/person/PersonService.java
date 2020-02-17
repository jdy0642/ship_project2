package com.ship.web.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
	@Autowired private PersonRepository personRepository;
	@Autowired private Person person;
	
	public void updatePoint(String personseq, String totalAmount) {
		person = personRepository.findById(Long.parseLong(personseq)).get();
		person.setPoint(String.valueOf(Integer.parseInt(person.getPoint())+Integer.parseInt(totalAmount)));
		personRepository.save(person);
	}
	
	public Person findBypersonseq(String personseq) {
		return personRepository.findById(Long.parseLong(personseq)).get();
	}
}
