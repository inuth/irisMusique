package be.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.DAO.MusicDAO;
import be.DAO.PersonDAO;
import be.DTO.person.PersonDTO;
import be.entities.Music;
import be.entities.Person;

@RestController
@RequestMapping("person")
public class PersonController {
	
	@Autowired
	private PersonDAO personDAO;
	
	@Autowired
	private MusicDAO musicDAO;
	
	@PutMapping("/{idPerson}/addMusic/{idMusic}")
	public ResponseEntity<PersonDTO> updateMusic(@PathVariable("idPerson") Integer idPerson,
			@PathVariable("idMusic") Integer idMusic){
		// chercher la personne
		Person p = personDAO.findById(idPerson).orElseThrow(ResourceNotFoundException::new);
		Music m = musicDAO.findById(idMusic).orElseThrow(() -> new ResourceNotFoundException("idmusic not found"));
		p.setMusic(m);
		
		return ResponseEntity.ok(new PersonDTO(personDAO.save(p)));
	}
	
	//@RequestMapping(value="", method = RequestMethod.GET)
	@GetMapping("")
	public ResponseEntity<List<PersonDTO>> get(Integer page, Integer nbResult){
		return ResponseEntity.ok(
				personDAO
				.getTop(PageRequest.of(page, nbResult))
				//.getFromNativ()
				.stream()
				.map(p -> {
					//System.out.println(p.getClass().getName());
					Person pers = new Person();
					pers.setId(p.getId());
					pers.setLastName(p.getLastName());
					pers.setFirstName(p.getFirstName());
					return new PersonDTO(pers);
					})
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
