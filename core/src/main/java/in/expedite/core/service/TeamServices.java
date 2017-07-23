package in.expedite.core.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.Department;
import in.expedite.core.entity.Team;
import in.expedite.core.entity.TeamMember;
import in.expedite.core.repository.TeamMemberRepository;
import in.expedite.core.repository.TeamRepository;

@Service
public class TeamServices {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TeamRepository teamRepository;
	
	@Autowired
	private DepartmentsService departmentService;
	
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
	
	public List<Team> getTeamForId(List<Long> ids){
		return teamRepository.findAll(ids);
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
	
	public List<Team> getTeamsForMembers(String userId){
		List<Team> teams=Collections.emptyList();
		List<Department> deptLst= departmentService.getDeptByManager(userId);
		
		if(deptLst.isEmpty()){
			teams=teamRepository.findByMember(userId);
		}else{
			List<Long> deptIdLst=new ArrayList<>();	
			deptLst.forEach( dept->{
				deptIdLst.add(dept.getId());
			});
			teams=teamRepository.findByDepartmentIdIn(deptIdLst);
			teams=Stream.concat(teams.stream(),teamRepository.findByMember(userId).stream())
					.distinct()
					.sorted()
					.collect(Collectors.toList());
		}
		teams=Stream.concat(teams.stream(), teamRepository.findByManagerId(userId).stream())
				.distinct()
				.sorted()
				.collect(Collectors.toList());
		
		return teams;
	}
}
