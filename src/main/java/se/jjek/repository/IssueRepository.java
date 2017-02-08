package se.jjek.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import se.jjek.model.Issue;
import se.jjek.model.WorkItem;

public interface IssueRepository extends CrudRepository<Issue, Long> {

	
	@Query("SELECT e FROM WorkItem e JOIN FETCH e.issue ed WHERE ed.id IS NOT NULL")
	Collection<WorkItem> getIssuedWorkItems();
	
}
