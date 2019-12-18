package be.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Music {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "music_id")
	private Integer id;
	
	private String title;
//	@ManyToMany(mappedBy = "musicListened")
//	private Set<Person> persons;
	
	@ManyToOne
	@JoinColumn(name = "autor_id")
	private Autor autor;
	private Integer duration;
	
	
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
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	
}
