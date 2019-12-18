package be.DTO.person;

import be.entities.Person;

public class PersonDTO {
	private Integer id;
	private String lastName;
	private String firstName;
	private PersonMusicDTO music;
	
	public PersonDTO(Person person) {
		if (person != null) {
			this.id = person.getId();
			this.lastName = person.getLastName();
			this.firstName = person.getFirstName();
			this.music = new PersonMusicDTO(person.getMusic());
		}
		
		
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public PersonMusicDTO getMusic() {
		return music;
	}
	public void setMusic(PersonMusicDTO music) {
		this.music = music;
	}
	
	
}
