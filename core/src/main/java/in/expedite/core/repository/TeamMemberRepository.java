package in.expedite.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import in.expedite.core.entity.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, Long> {

	
	
	 @Modifying
	 @Transactional
	public void deleteByUserIdAndTeamId(String userId,Long teamId);
}
