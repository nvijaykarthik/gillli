package in.expedite.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.Team;
import in.expedite.core.entity.TeamMember;
import in.expedite.core.repository.TeamMemberRepository;
import in.expedite.core.repository.TeamRepository;

@Service
public class TeamServices {

	@Autowired
	private TeamRepository teamRepository;
	
	
	@Autowired
	private TeamMemberRepository teamMemberRepository;
	
	public void addTeam(Team team,String username){
		team.setCreatedBy(username);
		team.setModifiedBy(username);
		team.setCreatedDate(new Date());
		team.setModifiedDate(new Date());
		teamRepository.save(team);
	}
	
	public void updateTeam(Team team,String username){
		team.setModifiedBy(username);
		team.setModifiedDate(new Date());
		teamRepository.save(team);
	}
	
	public List<Team> getAllTeam(){
		return teamRepository.findAll();
	}
	
	
	public void addTeamMember(TeamMember teamMember,String username) {
		teamMember.setCreatedBy(username);
		teamMember.setModifiedBy(username);
		teamMember.setCreatedDate(new Date());
		teamMember.setModifiedDate(new Date());
		teamMemberRepository.save(teamMember);
	}

	public void deleteMembersFromTeam(String userId,Long teamId) {
		teamMemberRepository.deleteByUserIdAndTeamId(userId,teamId);
	}
}
