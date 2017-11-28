package in.expedite.deployment.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.deployment.entity.TeamApproval;
import in.expedite.deployment.service.TeamApprovalService;
import in.expedite.deployment.utills.ExJsonResponse;

@RestController
@RequestMapping("/resource/teamApproval")
public class TeamApprovalController {

	@Autowired
	private TeamApprovalService approvalService;
	

	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public TeamApproval addTeamApproval(@RequestBody TeamApproval ta) {
		return approvalService.addTeamApproval(ta);
	}
	
	@RequestMapping(path="/ApproveOrReject",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public TeamApproval approve_reject(@RequestBody TeamApproval ta) {
		return approvalService.approve_reject(ta);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)		
	public List<TeamApproval> getTeamApprovalforDeployment(@RequestParam Long deploymentId){
		return approvalService.getTeamApprovalforDeployment(deploymentId);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)		
	public ExJsonResponse removeTeamApproval(@RequestParam Long appId){
		approvalService.removeTeamApproval(appId);
		return new ExJsonResponse(0, "Successfully deleted");
	}
}
