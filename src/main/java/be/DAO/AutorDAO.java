package be.DAO;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.entities.Autor;

public interface AutorDAO extends JpaRepository<Autor, Integer> {
	
	Optional<Autor> findByPseudo(String pseudo);
	
	@Query(value = "SELECT a FROM Autor a WHERE a.pseudo = :pseudo")
	Optional<Autor> GetByPseudo(@Param("pseudo")String pseudo);
	
	@Query(value = "SELECT autor_id, pseudonyme, reference FROM auteur WHERE autor_id = :id", 
			nativeQuery = true)
	Optional<Autor> GetAutorById(@Param("id") Integer id);
}
