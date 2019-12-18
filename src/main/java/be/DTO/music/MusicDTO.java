package be.DTO.music;

import be.entities.Music;

public class MusicDTO {
	private String title;
	private Integer duration;
	private String autorName;
	private Long nbFans;
	
	public MusicDTO() {}
	public MusicDTO(Music m) {
		this.title = m.getTitle();
		this.duration = m.getDuration();
		this.autorName = m.getAutor().getPseudo();
	}
	public MusicDTO(String title, Integer duration, String autorName, Long nbFans) {
		this.title = title;
		this.duration = duration;
		this.autorName = autorName;
		this.nbFans = nbFans;
	}
	
	public String getTitle() {
		System.out.println("ICI GET TITLE");
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
	public Long getNbFans() {
		return nbFans;
	}
	public void setNbFans(Long nbFans) {
		this.nbFans = nbFans;
	}
	
	
}	
