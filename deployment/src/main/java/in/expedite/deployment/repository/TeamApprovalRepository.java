package in.expedite.deployment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.expedite.deployment.entity.TeamApproval;

public interface TeamApprovalRepository extends JpaRepository<TeamApproval, Long> {
	List<TeamApproval> findByDeploymentId(Long deploymentId);
}
