package se.jjek.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Cascade;

@Entity
public class WorkItem extends AbstractEntity {

	private String itemName;
	private String description;
	@Enumerated(EnumType.STRING)
	private Status workStatus;
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne (cascade = CascadeType.REMOVE)
	@JoinColumn(unique = true)
	private Issue issue;
	
	protected WorkItem(){}

	public WorkItem(String itemName, String description, Status workStatus) {
		this.itemName = itemName;
		this.description = description;
		this.workStatus = workStatus;
		this.active = true;
	}
	
	public static enum Status {
		UNSTARTED, STARTED, DONE;
	}
	
	@Override
	public Long getId() {
		return super.getId();
	}

	public String getItemName() {
		return itemName;
	}
	
	public void setIssue(Issue issue) {
		this.issue = issue;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public Status getWorkStatus() {
		return workStatus;
	}
	
	public void setWorkStatus(Status workStatus) {
		this.workStatus = workStatus;
	}
	
	@Override
	public String toString() {
		return String.format("WorkItem: %s, Description: %s and Status: %s", itemName,description,workStatus);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof WorkItem) {
			WorkItem otherItem = (WorkItem) other;
			return itemName.equals(otherItem.itemName);
		}
		return false;
	}

	@Override
	public int hashCode() {

		int result = 1;
		result += 37 * itemName.hashCode();
		
		return result;
	}

}
