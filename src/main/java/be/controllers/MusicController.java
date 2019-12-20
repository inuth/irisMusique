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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.DAO.AutorDAO;
import be.DAO.MusicDAO;
import be.DTO.music.MusicDTO;
import be.DTO.music.MusicFilterDTO;
import be.DTO.music.MusicFilterV2DTO;
import be.DTO.music.MusicPostDTO;
import be.entities.Autor;
import be.entities.Music;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("music")
@Tag(name = "music", description = "the music api")
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
	
	@GetMapping("/v2")
	public ResponseEntity<List<MusicDTO>> get(MusicFilterV2DTO filters){	
		Stream<MusicDTO> musics = musicDAO
				.getFilteredV2(filters)
				.stream()
				.map(m -> new MusicDTO(m));
		return ResponseEntity.ok(musics.collect(Collectors.toList()));	
	}
	
//	@PutMapping
//	@DeleteMapping
    @Operation(summary = "Add a new music", description = "description to add a new music", tags = { "music" })
    //@io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(encoding = @Encoding(contentType = "application/json")))
    
    @ApiResponses(value = { 

        @ApiResponse(responseCode = "201", description = "Music created",

                content = @Content(schema = @Schema(implementation = MusicDTO.class))), 

        @ApiResponse(responseCode = "400", description = "Invalid input"), 

        @ApiResponse(responseCode = "409", description = "music already exists") })	
	@PostMapping(value="", consumes = {"application/json"})
	public ResponseEntity<MusicDTO> post(@Parameter(description="music to add. Cannot null or empty.", 

            required=true) @Valid @RequestBody MusicPostDTO music) throws Exception{
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
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Integer id) {
		Music music = musicDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException("You tried to delete a music not found."));
		
		// si lié, on supprime pas
		musicDAO.delete(music);
		return ResponseEntity.ok(true);
	}
}
