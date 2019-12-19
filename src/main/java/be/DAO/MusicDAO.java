package be.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.DTO.music.MusicDTO;
import be.DTO.music.MusicFilterDTO;
import be.DTO.music.MusicFilterV2DTO;
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
	
	@Query("SELECT m"
			+ " FROM Music m"
			+ " WHERE"
				+ " (:#{#filters.title} is null OR m.title LIKE CONCAT('%', CONCAT(:#{#filters.title}, '%')) )"
				+ " AND (:#{#filters.minDuration} is null OR :#{#filters.minDuration} <= m.duration)"
				+ " AND (:#{#filters.maxDuration} is null OR :#{#filters.maxDuration} >= m.duration)"
				+ " AND (:#{#filters.autorName} is null OR :#{#filters.autorName} = m.autor.pseudo)")
	List<Music> getFilteredV2(@Param("filters") MusicFilterV2DTO filters);
}
