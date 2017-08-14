package in.expedite.core.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.core.entity.Team;
import in.expedite.core.entity.TeamMember;
import in.expedite.core.service.TeamServices;
import in.expedite.core.utils.ExJsonResponse;


@RestController
@RequestMapping("/resource/team")
public class TeamController {

	@Autowired
	private TeamServices teamServices;
		
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse addTeam(@RequestBody Team team,@RequestAttribute(required=false) String username){
		teamServices.addTeam(team,username);
		return new ExJsonResponse(0, "Succesfully Added");
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Team> getTeam(){
		return teamServices.getAllTeam();
	}
	

	@RequestMapping(path="find",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Team> findTeam(@RequestParam(required=false) String q){
		return teamServices.findTeam(q);
	}
	
	@RequestMapping(path="/forIds", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Team> getTeam(@RequestBody List<Long> ids){
		return teamServices.getTeamForId(ids);
	}
	
	@RequestMapping(path="/addMember",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse addTeamMember(@RequestBody TeamMember teamMember,@RequestAttribute(required=false) String username){
		teamServices.addTeamMember(teamMember,username);
		return new ExJsonResponse(0, "Succesfully Added");
	}
	
	@RequestMapping(path="/members",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse deleteMemberFromTeam(@RequestParam String userId ,@RequestParam Long teamId){
		 teamServices.deleteMembersFromTeam(userId,teamId);
		return new ExJsonResponse(0, "Succesfully Deleted");
	}
	
	@RequestMapping(path="/myTeams",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Team> myTeams(@RequestAttribute(required=false) String username){
		return teamServices.getTeamsForMembers(username);
	}
	
	
}
