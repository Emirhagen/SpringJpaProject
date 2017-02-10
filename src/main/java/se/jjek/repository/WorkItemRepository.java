package se.jjek.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import se.jjek.model.Team;
import se.jjek.model.User;
import se.jjek.model.WorkItem;
import se.jjek.model.WorkItem.Status;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {

	Collection<WorkItem> findWorkItemsByWorkStatus(Status workStatus);

	Collection<WorkItem> findWorkItemByUserTeam(Team team);
	
	Collection<WorkItem> findWorkItemByUser(User user);
	
	Collection<WorkItem> findByDescriptionContaining(String description);
	
}
