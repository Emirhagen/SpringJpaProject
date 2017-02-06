package se.jjek.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import se.jjek.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

	Collection<User> findUserByTeamId(long id);

	@Query("SELECT u FROM #{#entityName} u WHERE u.userName LIKE CONCAT(Rtrim(:uName),'%') AND u.firstName LIKE CONCAT(Rtrim(:fName),'%') AND u.lastName LIKE CONCAT(Rtrim(:lName),'%')")
	Collection<User> getUsersByNames(@Param("uName") String userName, @Param("fName") String firstName,
			@Param("lName") String lastName);

}
