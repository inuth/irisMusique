package be.DTO.person;

import be.entities.Music;

public class PersonMusicDTO {
	private Integer id;
	private String title;
	private Integer duration;
	private String autorName;
	
	public PersonMusicDTO(Music music) {
		if (music != null) {
			this.id = music.getId();
			this.title = music.getTitle();
			this.duration = music.getDuration();
			if (music.getAutor() != null) {
				this.autorName = music.getAutor().getPseudo();
			}
			
		}
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getAutorName() {
		return autorName;
	}
	public void setAutorName(String autorName) {
		this.autorName = autorName;
	}
	
	
}
