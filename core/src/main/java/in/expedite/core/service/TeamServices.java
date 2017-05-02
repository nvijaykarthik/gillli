package in.expedite.core.service;

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
	
	public void addTeam(Team team){
		teamRepository.save(team);
	}
	
	public List<Team> getAllTeam(){
		return teamRepository.findAll();
	}
	
	
	public void addTeamMember(TeamMember teamMember) {
		teamMemberRepository.save(teamMember);
	}

	public void deleteMembersFromTeam(String userId,Long teamId) {
		teamMemberRepository.deleteByUserIdAndTeamId(userId,teamId);
	}
}
