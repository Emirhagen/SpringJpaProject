package se.jjek.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import se.jjek.model.Team;
import se.jjek.model.User;
import se.jjek.repository.TeamRepository;
import se.jjek.repository.UserRepository;

@Component
public class EntityService {

	private TeamRepository teamRepository;
	private UserRepository userRepository;

	@Autowired
	public EntityService(TeamRepository teamRepository, UserRepository userRepository) {
		this.teamRepository = teamRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void addUserToTeam(User user, Team team) throws ServiceException {
		Team dbTeam = teamRepository.findOne(team.getId());
		User dbUser = userRepository.findOne(user.getId());
		try {
			if (dbTeam.getUsers().size() < 10) {
				Collection<User> users = dbTeam.getUsers();
				users.add(dbUser);
				dbTeam.setUsers(users);
				dbUser.setTeam(dbTeam);
				addUser(dbUser);
				addTeam(dbTeam);
			} else {
				throw new ServiceException(
						user + " could not be added to team " + team + ", a team can only have 10 members");
			}

		} catch (Exception e) {
			throw new ServiceException("User " + user + " could not be added to " + team, e);
		}
	}

	public Team findTeamById(Long teamId) {
		return teamRepository.findOne(teamId);
	}

	public User findUserById(Long userId) {
		return userRepository.findOne(userId);
	}

	public Team addTeam(Team team) {
		return teamRepository.save(team);
	}

	public User addUser(User user) {
		return userRepository.save(user);
	}

	public Team deactivateTeam(Team team) {
		team.setActive(false);
		return teamRepository.save(team);
	}

	public Collection<Team> getAllTeams() {
		Collection<Team> teams = (Collection<Team>) teamRepository.findAll();
		return teams;
	}
}
