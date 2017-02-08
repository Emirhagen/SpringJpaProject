package se.jjek.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

@Entity
public class Team extends AbstractEntity {

	private String teamname;
	private boolean active;

	@OneToMany(mappedBy = "team")
	private Set<User> users;

	protected Team() {
	}

	public Team(String teamname, boolean active) {
		this.teamname = teamname;
		this.active = active;
	}

	@Override
	public Long getId() {
		return super.getId();
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getTeamName() {
		return teamname;
	}

	public Set<User> getUsers() {
		return users;
	}

	public boolean isActive() {
		return active;
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof Team) {
			Team otherTeam = (Team) other;
			return teamname.equals(otherTeam.teamname);
		}

		return false;
	}

	@Override
	public int hashCode() {

		int result = 1;
		result += 37 * teamname.hashCode();

		return result;
	}

}
