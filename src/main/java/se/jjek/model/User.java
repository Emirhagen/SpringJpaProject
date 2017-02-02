package se.jjek.model;

public final class User {

	private final String userName;
	private int userStatus;
	private int teamId;

	public User(String userName, int userStatus,int teamId) {
		this.userName = userName;
		this.userStatus = userStatus;
		this.teamId = teamId;
	}
	public int getTeamId() {
		return teamId;
	}

	public String getUserName() {
		return userName;
	}

	public int getUserStatus() {
		return userStatus;
	}
	
	@Override
	public String toString() {
		return String.format("User: %s", userName);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof User) {
			User otherUser = (User) other;
			return userName.equals(otherUser.userName);
		}
		return false;
	}

	@Override
	public int hashCode() {

		int result = 1;
		result += 37 * userName.hashCode();

		return result;
	}
}
