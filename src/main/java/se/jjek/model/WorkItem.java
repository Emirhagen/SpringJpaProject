package se.jjek.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class WorkItem extends AbstractEntity {

	private String itemName;
	private String description;
	private int workStatus;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@JoinColumn(unique = true)
	private Issue issue;
	
	
	protected WorkItem(){}

	public WorkItem(String itemName, String description, int workStatus) {
		this.itemName = itemName;
		this.description = description;
		this.workStatus = workStatus;
		
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
	
	public void setUser(User user) {
		this.user = user;
	}

	public String getDescription() {
		return description;
	}

	public int getWorkStatus() {
		return workStatus;
	}
	
	public void setWorkStatus(int workStatus) {
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
