package in.expedite.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class TeamMember {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="teamMembSeq")
	@SequenceGenerator(name="teamMembSeq",allocationSize=1,initialValue=100)
	private Long id;
	
	@Column
	private Long teamId;
	
	@Column
	private String userId;
	
	@Column
	private Integer allocationPercent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getAllocationPercent() {
		return allocationPercent;
	}

	public void setAllocationPercent(Integer allocationPercent) {
		this.allocationPercent = allocationPercent;
	}
	
}
