package be.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.DAO.PersonDAO;
import be.DTO.person.PersonDTO;

@RestController
@RequestMapping("person")
public class PersonController {
	
	@Autowired
	private PersonDAO personDAO;
	
	//@RequestMapping(value="", method = RequestMethod.GET)
	@GetMapping("")
	public ResponseEntity<List<PersonDTO>> get(){
		return ResponseEntity.ok(personDAO.findAll()
				.stream()
				.map(p -> new PersonDTO(p))
				.collect(Collectors.toList()));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PersonDTO> get(@PathVariable Integer id){
		return ResponseEntity.ok(
				new PersonDTO(
						personDAO.findById(id)
						.orElse(null)
						)
				);
	}
	
}
