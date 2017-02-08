package se.jjek.repository;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;

import se.jjek.model.Team;
import se.jjek.model.User;
import se.jjek.model.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {

	Collection<WorkItem> findWorkItemsByWorkStatus(int workStatus);

	// @Query("SELECT e FROM Workitem e join fetch e.users_id ed WHERE
	// ed.team_id = ?1")
	Collection<WorkItem> findWorkItemByUserTeam(Team team);
	
	Collection<WorkItem> findWorkItemByUser(User user);
	
	Collection<WorkItem> findByDescriptionLike(String description);
	
}
