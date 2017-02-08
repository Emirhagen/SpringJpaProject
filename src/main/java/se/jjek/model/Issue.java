package se.jjek.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Issue extends AbstractEntity {

	private String issue;
	private String specifiedIssue;

	@OneToOne(mappedBy = "issue")
	@JoinColumn(unique = true)
	private WorkItem workItem;
	
	protected Issue(){}

	public Issue(String issue, String specifiedIssue) {
		this.issue = issue;
		this.specifiedIssue = specifiedIssue;
	}
	
	@Override
	public Long getId() {
		return super.getId();
	}

	public String getIssue() {
		return issue;
	}

	public WorkItem getWorkItem() {
		return workItem;
	}

	public String getSpecifiedIssue() {
		return specifiedIssue;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof Issue) {
			Issue otherIssue = (Issue) other;
			return issue.equals(otherIssue.issue);
		}

		return false;
	}

	@Override
	public int hashCode() {

		int result = 1;
		result += 37 * issue.hashCode();

		return result;
	}

}
