package be.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.DAO.MusicDAO;
import be.DTO.music.MusicDTO;

@RestController
@RequestMapping("music")
public class MusicController {
	
	@Autowired
	private MusicDAO musicDAO;
	
	@GetMapping("/test/{id}")
	public ResponseEntity<MusicDTO> test(@PathVariable("id") Integer id) {
		MusicDTO m = musicDAO.getMusicDTO(id).orElse(null);
		return ResponseEntity.ok(m);
	}
}
