package be.DTO.music;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MusicPostDTO {
	@Min(value = 100, message = "trop ptit !")
	@Max(600)
	private Integer duration;
	@Size(min = 3, max = 50)
	@NotNull
	//@NotEmpty
	private String title;
	@NotNull
	private Integer autorId;
	
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getAutorId() {
		return autorId;
	}
	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	
}
