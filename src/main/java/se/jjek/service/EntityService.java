package se.jjek.service;

import java.util.Collection;

import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import se.jjek.model.Issue;
import se.jjek.model.Team;
import se.jjek.model.User;
import se.jjek.model.WorkItem;
import se.jjek.model.WorkItem.Status;
import se.jjek.repository.IssueRepository;
import se.jjek.repository.TeamRepository;
import se.jjek.repository.UserRepository;
import se.jjek.repository.WorkItemRepository;

@Component
public final class EntityService {

	private static final int MAX_LENGHT_USERNAME = 10;
	private final UserRepository userRepository;
	private final TeamRepository teamRepository;
	private final WorkItemRepository workItemRepository;
	private final IssueRepository issueRepository;
	private final ServiceTransaction executor;

	@Autowired
	public EntityService(UserRepository userRepository, TeamRepository teamRepository,
			WorkItemRepository workItemRepository, IssueRepository issueRepository, ServiceTransaction executor) {
		this.userRepository = userRepository;
		this.teamRepository = teamRepository;
		this.workItemRepository = workItemRepository;
		this.issueRepository = issueRepository;
		this.executor = executor;
	}

	public User saveOrUpdateUser(User user) {
		if (user.getUsername().length() < MAX_LENGHT_USERNAME) {
			throw new ServiceException("Username too short.");
		}
		return userRepository.save(user);
	}

	public User inactivateUser(Long userId) {
		try {
			return executor.execute(() -> {
				User user = userRepository.findOne(userId);
				user.setActive(false);
				setItemStatus(user);
				return userRepository.save(user);
			});
		} catch (DataAccessException e) {
			throw new ServiceException("Could not inactivate user " + e);
		}
	}

	public User addUserToTeam(Long teamId, Long userId) {
		try {
			if (!isValidNumbersOfUsers(teamId)) {
				throw new ServiceException("Max 10 users in team.");
			}

			return executor.execute(() -> {
				User user = userRepository.findOne(userId);
				Team team = teamRepository.findOne(teamId);
				user.setTeam(team);
				return userRepository.save(user);
			});
		} catch (DataAccessException e) {
			throw new ServiceException("Could not add user to team. " + e);
		}
	}

	public User findUserByUsername(String username) {
		return userRepository.findUserByUsername(username);
	}

	public User findUserByNumber(String number) {
		return userRepository.findUserByNumber(number);
	}

	public Collection<User> findByNameDescriptions(String firstName, String lastName, String username) {
		return userRepository.searchUserByNamesContaining(username, firstName, lastName);
	}

	public Collection<User> getAllUsersInATeam(Long id) {
		return userRepository.getAllUsersInATeam(id);
	}

	public Team saveOrUpdateTeam(Team team) {
		return teamRepository.save(team);
	}

	public Team inactivateTeam(Long teamId) {
		Team team = teamRepository.findOne(teamId);
		team.setActive(false);
		return teamRepository.save(team);
	}

	public Collection<Team> getAllTeams() {
		return teamRepository.getAllTeams();
	}

	public WorkItem saveOrUpdateWorkItem(WorkItem workItem) {
		return workItemRepository.save(workItem);
	}

	public WorkItem changeWorkStatus(Long itemId, Status workStatus) {
		try {
			return executor.execute(() -> {
				WorkItem workItem = workItemRepository.findOne(itemId);
				workItem.setWorkStatus(workStatus);
				return workItemRepository.save(workItem);
			});
		} catch (DataAccessException e) {
			throw new ServiceException("Could not change status" + e);
		}
	}

	public void deleteWorkItem(Long itemId) {
		workItemRepository.delete(itemId);
	}

	public WorkItem assignWorkItem(Long itemId, Long userId) {
		try {
			if (!isActiveUser(userId)) {
				throw new ServiceException("User is not active.");
			}
			if (!isValidNumbersOfItems(userId)) {
				throw new ServiceException("Maximum amount of Workitems.");
			}
			return executor.execute(() -> {
				WorkItem item = workItemRepository.findOne(itemId);
				User user = userRepository.findOne(userId);
				item.setUser(user);
				userRepository.save(user);
				return workItemRepository.save(item);
			});
		} catch (DataAccessException e) {
			throw new ServiceException("Could not assign item. " + e);
		}
	}

	public Collection<WorkItem> getWorkItemsByStatus(Status workStatus) {
		return workItemRepository.findWorkItemsByWorkStatus(workStatus);
	}

	public Collection<WorkItem> getWorkItemsFromTeam(Long teamId) {
		Team team = teamRepository.findOne(teamId);
		return workItemRepository.findWorkItemByUserTeam(team);
	}

	public Collection<WorkItem> getWorkItemsByUser(Long userId) {
		User user = userRepository.findOne(userId);
		return workItemRepository.findWorkItemByUser(user);
	}

	public Collection<WorkItem> getWorkItemByDescription(String description) {
		return workItemRepository.findByDescriptionContaining(description);
	}

	public Issue saveOrUpdateIssue(Issue issue, Long itemId) {
		try {
			if (!workItemIsDone(itemId)) {
				throw new ServiceException("Workitem is not done.");
			}

			return executor.execute(() -> {
				WorkItem item = workItemRepository.findOne(itemId);
				item.setWorkStatus(Status.UNSTARTED);
				issueRepository.save(issue);
				item.setIssue(issue);
				saveOrUpdateWorkItem(item);
				return issue;
			});
		} catch (DataAccessException e) {
			throw new ServiceException("Could not save or update issue. " + e);
		}
	}

	public Collection<WorkItem> getIssuedWorkItems() {
		return issueRepository.getIssuedWorkItems();
	}

	private void setItemStatus(User user) {
		Collection<WorkItem> items = getWorkItemsByUser(user.getId());
		for (WorkItem currentItem : items) {
			currentItem.setWorkStatus(Status.UNSTARTED);
			workItemRepository.save(currentItem);
		}

	}

	private boolean isValidNumbersOfUsers(Long teamId) {
		return getAllUsersInATeam(teamId).size() < 10;
	}

	private boolean isActiveUser(Long userId) {
		return userRepository.findOne(userId).isActive();
	}

	private boolean isValidNumbersOfItems(Long userId) {
		return getWorkItemsByUser(userId).size() < 5;
	}

	private boolean workItemIsDone(Long itemId) {
		return workItemRepository.findOne(itemId).getWorkStatus() == Status.DONE;
	}

}
