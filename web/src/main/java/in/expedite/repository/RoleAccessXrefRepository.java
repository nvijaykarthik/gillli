package in.expedite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import in.expedite.entity.RoleAccessXref;

public interface RoleAccessXrefRepository extends JpaRepository<RoleAccessXref, Long> {

	public List<RoleAccessXref> findByRoleCode(String roleCode);
	
	@Modifying
	@Query("delete from RoleAccessXref where roleCode=:roleCode and accessCode=:accessCode")
	public void delete(@Param(value = "roleCode") String roleCode,@Param(value = "accessCode") String accessCode);
	
	public List<RoleAccessXref> findByRoleCodeIn(List<String> roleCode); 
}
