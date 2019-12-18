package be.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.DAO.AutorDAO;
import be.DAO.MusicDAO;
import be.DTO.music.MusicDTO;
import be.DTO.music.MusicPostDTO;
import be.entities.Autor;
import be.entities.Music;

@RestController
@RequestMapping("music")
public class MusicController {
	
	@Autowired
	private MusicDAO musicDAO;
	
	@Autowired
	private AutorDAO autorDAO;
	
	@GetMapping("/test/{id}")
	public ResponseEntity<MusicDTO> test(@PathVariable("id") Integer id) {
		MusicDTO m = musicDAO.getMusicDTO(id).orElse(null);
		return ResponseEntity.ok(m);
	}
	
	@PostMapping("")
	public ResponseEntity<MusicDTO> post(@Valid @RequestBody MusicPostDTO music){
		System.out.println("coucou");
		Autor autor = autorDAO.findById(music.getAutorId()).orElse(null);
		Music newMusic = new Music(music);
		newMusic.setAutor(autor);
		return ResponseEntity.ok(new MusicDTO(musicDAO.save(newMusic)));
	}
}
