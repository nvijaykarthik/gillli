package in.expedite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.expedite.entity.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	@Modifying
	@Query("delete from UserRole where userId=:userId and roleCode=:roleCode")
	public void deleteByUserIdAndRoleCode(@Param("userId") String userId,@Param("roleCode") String roleCode);
	
	public List<UserRole> findByUserId(String userId);
	
}
