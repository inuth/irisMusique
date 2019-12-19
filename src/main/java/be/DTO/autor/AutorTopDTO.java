package be.DTO.autor;

public class AutorTopDTO {
	private Integer id;
	private String pseudo;
	private String reference;
	private Long fans;
	
	public AutorTopDTO() {}
	
	public AutorTopDTO(Integer id, String pseudo, String reference, Long fans) {
		this.id = id;
		this.pseudo = pseudo;
		this.reference = reference;
		this.fans = fans;
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
	public Long getFans() {
		return fans;
	}
	public void setFans(Long fans) {
		this.fans = fans;
	}
	
	
}
