package se.jjek.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.jjek.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findUserByNumber(String number);
	
	User findUserByUsername(String username);
	
	@Query("SELECT e FROM User e WHERE username LIKE ? OR firstname LIKE ? OR lastname LIKE ?")
	Collection<User> searchUserByNames(String username, String firstName, String lastName);
	
	@Query("SELECT e from User e join fetch e.team ed WHERE ed.id = ?1")
	Collection<User> getAllUsersInATeam(Long team_id);
		
}
