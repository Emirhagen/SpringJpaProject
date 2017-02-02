package se.jjek.model;


public final class Issue {

	private final String issue;
	private final String specifiedIssue;

	public Issue(String issue, String specifiedIssue) {
		this.issue = issue;
		this.specifiedIssue = specifiedIssue;
	}

	public String getIssue() {
		return issue;
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
