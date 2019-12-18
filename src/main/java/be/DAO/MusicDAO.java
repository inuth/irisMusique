package be.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.DTO.music.MusicDTO;
import be.DTO.music.MusicFilterDTO;
import be.entities.Music;

public interface MusicDAO extends JpaRepository<Music, Integer>{
	@Query("SELECT NEW be.DTO.music.MusicDTO (m.title, m.duration, m.autor.pseudo, COUNT(p))"
			+ " FROM Music m LEFT JOIN Person p ON p.music = m"
			+ " WHERE m.id = :id"
			+ " GROUP BY m.title, m.duration, m.autor.pseudo")
	Optional<MusicDTO> getMusicDTO(@Param("id") Integer id);
	
	@Query("SELECT m FROM Music m"
			+ " WHERE m.duration = :#{#filters.duration}"
			+ " AND m.title LIKE CONCAT('%', CONCAT(:#{#filters.title}, '%'))"
			+ " AND m.autor.pseudo = :#{#filters.autorName}"
			)
	List<Music> getFiltered(@Param("filters") MusicFilterDTO filters);
}
