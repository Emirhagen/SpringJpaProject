package se.jjek.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.jjek.model.Team;

public interface TeamRepository extends CrudRepository<Team, Long> {

	@Query("SELECT e FROM Team e")
	Collection<Team> getAllTeams();
	
}
