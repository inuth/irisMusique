package be.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.DAO.AutorDAO;
import be.DAO.MusicDAO;
import be.DTO.music.MusicDTO;
import be.DTO.music.MusicFilterDTO;
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
	
	@GetMapping("")
	public ResponseEntity<List<MusicDTO>> get(MusicFilterDTO filters){
		System.out.println(filters.getTitle());
		Stream<MusicDTO> musics = musicDAO
				.getFiltered(filters)
				.stream()
				.map(m -> new MusicDTO(m));
		musics = musics.filter(m -> m.getTitle().length() >= 0);
		System.out.println("AVANT LE RETURN");
		return ResponseEntity.ok(musics.collect(Collectors.toList()));
	}
//	@PutMapping
//	@DeleteMapping
	@PostMapping("")
	public ResponseEntity<MusicDTO> post(@Valid @RequestBody MusicPostDTO music) throws Exception{
		System.out.println("coucou");
		Autor autor = autorDAO
				.findById(music.getAutorId())
				.orElseThrow(() -> new ResourceNotFoundException("no autor with id : " + music.getAutorId()));
//		Autor autor = autorDAO
//				.findById(music.getAutorId())
//				.orElseThrow(ResourceNotFoundException::new);
		Music newMusic = new Music(music);
		newMusic.setAutor(autor);
		return ResponseEntity.ok(new MusicDTO(musicDAO.save(newMusic)));
	}
}
