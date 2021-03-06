package in.expedite.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import in.expedite.core.entity.Team;

public interface TeamRepository extends PagingAndSortingRepository<Team,Long>,JpaRepository<Team, Long> {

	public List<Team> findByDepartmentIdIn(List<Long> departmentIdList);
	
	@Query("select t from Team t where t.id in(select tm.teamId from TeamMember tm where tm.userId=:userId)")
	public List<Team> findByMember(@Param(value="userId") String userId);
	
	public List<Team> findByManagerId(@Param(value="managerId") String managerId);
	
	List<Team> findByTeamNameIgnoreCaseContaining(String teamName);
}
