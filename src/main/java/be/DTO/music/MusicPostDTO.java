package be.DTO.music;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class MusicPostDTO {
    @Schema(description = "duration in seconds", 
            example = "210")
	@Min(value = 100, message = "trop ptit !")
	@Max(600)
	private Integer duration;
	@Size(min = 3, max = 50)
	@NotNull
	//@NotEmpty
	@Schema(description = "title", 
    example = "title of the music")
	private String title;
	@NotNull
	@Schema(description = "id of the autor", 
    example = "1", required = true, minimum = "5")
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
