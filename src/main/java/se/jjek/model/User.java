package se.jjek.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class User extends AbstractEntity {

	@Column(nullable = false)
	private String userName;
	@Column(nullable = false)
	private String firstName;
	@Column(nullable = false)
	private String lastName;
	private boolean active;

	@OneToMany(mappedBy = "user")
	private Collection<WorkItem> workItems;
	
	@ManyToOne
	private Team team;
	
	protected User(){}

	public User(String userName, String firstName, String lastName, boolean active) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.active = active;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public boolean isActiveUser() {
		return active;
	}

	public void setActiveUser(boolean activeUser) {
		this.active = active;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

	@Override
	public String toString() {
		return "User [id=" + super.getId() + ", userName=" + userName + ", firstName=" + firstName + ", lastName="
				+ lastName + ", activeUser=" + active + ", teamId=" + team.getId() + "]\n";
	}
}
