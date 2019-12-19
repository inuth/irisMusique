package be.DAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import be.DTO.person.IPersonDTO;
import be.entities.Person;

public interface PersonDAO extends JpaRepository<Person, Integer>{
	@Query("SELECT p FROM Person p ORDER BY p.id")
	Page<Person> getTop(Pageable pageable);
	
	@Query(value="SELECT p.person_id AS id, p.last_name AS lastName, p.first_name AS firstName FROM person p",
			nativeQuery = true)
	List<IPersonDTO> getFromNativ();
}
