package be.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.DTO.autor.AutorTopDTO;
import be.entities.Autor;

public interface AutorDAO extends JpaRepository<Autor, Integer> {
	
	Optional<Autor> findByPseudo(String pseudo);
	
	@Query(value = "SELECT a FROM Autor a WHERE a.pseudo = :pseudo")
	Optional<Autor> GetByPseudo(@Param("pseudo")String pseudo);
	
	@Query(value = "SELECT autor_id, pseudonyme, reference FROM auteur WHERE autor_id = :id", 
			nativeQuery = true)
	Optional<Autor> GetAutorById(@Param("id") Integer id);
	
	@Query("SELECT NEW be.DTO.autor.AutorTopDTO(a.id, a.pseudo, a.reference, COUNT(p) AS fans)"
			+ " FROM Autor a JOIN a.musics m JOIN Person p ON p.music = m"
			+ " GROUP BY a.id, a.pseudo, a.reference"
			+ " ORDER BY fans DESC")
	public Page<AutorTopDTO> getTopX(Pageable page);
	
	@Query(value="SELECT p.top, a.pseudonyme"
			+ " FROM (SELECT music_music_id, COUNT(music_music_id) top"
				+ " FROM Person"
				+ " GROUP BY music_music_id) p"
			+ " JOIN Music m ON m.music_id = p.music_music_id"
			+ " JOIN Auteur a ON m.auteur_id = a.auteur_id",
			nativeQuery = true)
	public List<IAutorTopDTO> getTopAutorDTO();
	
	public static interface IAutorTopDTO{
		Integer getTop();
		String getPseudonyme();
	}
	
}
