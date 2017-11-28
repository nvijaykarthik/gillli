package in.expedite.deployment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.deployment.entity.TeamApproval;
import in.expedite.deployment.repository.TeamApprovalRepository;

@Service
public class TeamApprovalService {

	@Autowired
	private TeamApprovalRepository teamApprovalRepository;
	
	
	@Autowired
	private DeploymentService deploymentService;
	
	public TeamApproval addTeamApproval(TeamApproval ta) {
		return teamApprovalRepository.save(ta);
	}
	
	public TeamApproval approve_reject(TeamApproval ta) {
		TeamApproval teamApproval=teamApprovalRepository.save(ta);
		List<TeamApproval> teamAppList=this.getTeamApprovalforDeployment(ta.getDeploymentId());
		deploymentService.processDeploymentStatusOnApproval(teamAppList, ta.getDeploymentId());
		
		return teamApproval;
	}
	
	public List<TeamApproval> getTeamApprovalforDeployment(Long deploymentId){
		return teamApprovalRepository.findByDeploymentId(deploymentId);
	}


	public void removeTeamApproval(Long appId) {
		teamApprovalRepository.delete(appId);
	}
	
	
}
