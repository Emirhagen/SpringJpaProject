package se.jjek.model;

public final class Team extends AbstractEntity {

	private final String teamName;

	public Team(String teamName) {
		this.teamName = teamName;
	}

	public String getTeamName() {
		return teamName;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof Team) {
			Team otherTeam = (Team) other;
			return teamName.equals(otherTeam.teamName);
		}

		return false;
	}

	@Override
	public int hashCode() {

		int result = 1;
		result += 37 * teamName.hashCode();

		return result;
	}

}
