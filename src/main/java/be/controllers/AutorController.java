package be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.DAO.AutorDAO;
import be.DTO.autor.AutorDTO;
import be.DTO.autor.AutorTopDTO;
import be.entities.Autor;

@RequestMapping("autor")
@RestController
public class AutorController {
	
	@Autowired
	private AutorDAO autorDAO;
	
	@GetMapping("/search/{pseudo}")
	public ResponseEntity<AutorDTO> searchBy(@PathVariable String pseudo){
		return ResponseEntity.ok(new AutorDTO(autorDAO.findByPseudo(pseudo).orElseThrow(ResourceNotFoundException::new)));
	}
	
	@PutMapping("/{idAutor}")
	public ResponseEntity<AutorDTO> update(@PathVariable Integer idAutor, @RequestBody String pseudo)
	{
		Autor autor = autorDAO.findById(idAutor).orElseThrow(ResourceNotFoundException::new);
		autor.setPseudo(pseudo);
		return ResponseEntity.ok(new AutorDTO(autorDAO.save(autor)));		
	}
	
	@GetMapping("/top/{nb}")
	public ResponseEntity<Page<AutorTopDTO>> getTopX(@PathVariable Integer nb){
		return ResponseEntity.ok(autorDAO.getTopX(PageRequest.of(0, nb)));
	}
}
