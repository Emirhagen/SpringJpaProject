package se.jjek.model;

public final class WorkItem extends AbstractEntity {

	private final String itemName;
	private final String description;
	private final int workStatus;

	public WorkItem(String itemName, String description, int workStatus) {
		this.itemName = itemName;
		this.description = description;
		this.workStatus = workStatus;
	}

	public String getItemName() {
		return itemName;
	}

	public String getDescription() {
		return description;
	}

	public int getWorkStatus() {
		return workStatus;
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
