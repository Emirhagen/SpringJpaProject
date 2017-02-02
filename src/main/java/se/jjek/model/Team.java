package se.jjek.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public final class Team extends AbstractEntity {

	private String teamName;
	private boolean active;

	@OneToMany
	private Collection<User> users;

	public Team(String teamName, boolean active) {
		this.teamName = teamName;
		this.active = active;
	}
	
	@Override
	public Long getId() {
		return super.getId();
	}
	
	public String getTeamName() {
		return teamName;
	}
	
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}	
	
	public Collection<User> getUsers() {
		return users;
	}

	public void setUsers(Collection<User> users) {
		this.users = users;
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
	
	@Override
	public String toString() {
		return getId() + ", " + teamName;
	}

}
