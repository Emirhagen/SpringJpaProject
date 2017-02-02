package se.jjek.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import se.jjek.model.WorkItem;

public interface WorkItemRepository extends CrudRepository<WorkItem, Long> {
	
	Collection<WorkItem> getWorkItemByWorkStatus();
	Collection<WorkItem> getWorkItemByUserTeam();
	
}
