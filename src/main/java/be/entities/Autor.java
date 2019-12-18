package be.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Auteur")
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "autor_id")
	private Integer id;
	
	@Column(name="pseudonyme")
	private String pseudo;
	
	@Column(unique = true)
	private String reference;
	@OneToMany(mappedBy = "autor")
	private Set<Music> musics;

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

	public Set<Music> getMusics() {
		return musics == null ? (musics = new HashSet<>()) : musics;
	}

	public void setMusics(Set<Music> musics) {
		this.musics = musics;
	}
	
	
}
