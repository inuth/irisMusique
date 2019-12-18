package be.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import be.entities.Person;

public interface PersonDAO extends JpaRepository<Person, Integer>{

}
