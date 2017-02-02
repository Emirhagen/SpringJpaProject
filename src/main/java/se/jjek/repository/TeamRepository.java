package se.jjek.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import se.jjek.model.Team;
import se.jjek.model.User;

public interface TeamRepository extends PagingAndSortingRepository<Team, Long> {

	Team addUserToTeam(User user);
	
	
	/*
		* Skapa ett team
		* Uppdatera ett team
		* Inaktivera ett team
		* Hämta alla team
		* Lägga till en User till ett Team
	 */
}
