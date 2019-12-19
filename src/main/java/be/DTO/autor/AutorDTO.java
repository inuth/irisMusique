package be.DTO.autor;

import be.entities.Autor;

public class AutorDTO {
	private Integer id;
	private String pseudo;
	private String reference;
	
	public AutorDTO() {}
	
	public AutorDTO(Autor autor) {
		this.id = autor.getId();
		this.pseudo = autor.getPseudo();
		this.reference = autor.getReference();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	
}
