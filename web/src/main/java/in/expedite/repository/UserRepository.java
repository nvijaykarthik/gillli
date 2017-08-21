package in.expedite.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.expedite.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

	public User findByUserIdAndPassword(String userId,String password); 
	
	@Query("Select u from User u where u.userId=?1")
	public User findUserIdQuery(String userId); 

	public Iterable<User> findByFirstNameContainingIgnoreCaseOrSecondNameContainingIgnoreCase(String firstName, String secondName);
	
	@Query("Select u from User u where u.userId in (select T.userId from TeamMember T where T.teamId=:teamId)")
	public List<User> getMembersForTeam(@Param(value = "teamId") Long teamId);

	public User findByUserId(String loginId);
	
}
