package se.jjek.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User extends AbstractEntity {

	private String firstName;
	private String lastName;
	private String username;
	private boolean active;
	private String number;

	@OneToMany(mappedBy = "user")
	private Collection<WorkItem> workItems;

	@ManyToOne
	private Team team;

	protected User() {
	}

	public User(String firstName, String lastName, String username, boolean active, String number) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.active = active;
		this.number = number;

	}

	@Override
	public Long getId() {
		return super.getId();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNumber() {
		return number;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Team getTeam() {
		return team;
	}

	public String getUsername() {
		return username;
	}

	public boolean isActive() {
		return active;
	}

	public Collection<WorkItem> getWorkItems() {
		return workItems;
	}

	@Override
	public String toString() {
		return String.format("User: %s", username);
	}

	@Override
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}

		if (other instanceof User) {
			User otherUser = (User) other;
			return username.equals(otherUser.username);
		}
		return false;
	}

	@Override
	public int hashCode() {

		int result = 1;
		result += 37 * username.hashCode();

		return result;
	}
}
