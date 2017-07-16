package in.expedite.resourcePlan.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class ResourcePlan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String resourceId;
	
	@Column
	private Long  projectId;
	
	@Column
	private Long  teamId;
	
	@Column
	private String phase;
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd") 
	private Date startDate;
	
	@Column
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern="yyyy-MM-dd") 
	private Date endDate;
	
	@Column
	private Double totalEffort;
	
	@Column
	private Double effortPerDay;
	
	@Column
	private Integer effortPercent;
	
	public Integer getEffortPercent() {
		return effortPercent;
	}


	public void setEffortPercent(Integer effortPercent) {
		this.effortPercent = effortPercent;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date createdDate=new Date();
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date modifiedDate=new Date();
	
	@Column
	private String createdBy;
	
	@Column
	private String modifiedBy;
	
	@Column(length=4096)
	private String comments;
	
	@Transient
	private String projectName;

	
	@Transient
	private String resourceName;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}
	
	public void setBigintId(BigInteger id) {
		this.id = id.longValue();
	}


	public String getResourceId() {
		return resourceId;
	}


	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public void setBigintProjectId(BigInteger projectId) {
		this.projectId = projectId.longValue();
	}

	public String getPhase() {
		return phase;
	}


	public void setPhase(String phase) {
		this.phase = phase;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}





	public Double getTotalEffort() {
		return totalEffort;
	}


	public void setTotalEffort(Double totalEffort) {
		this.totalEffort = totalEffort;
	}


	public Double getEffortPerDay() {
		return effortPerDay;
	}


	public void setEffortPerDay(Double effortPerDay) {
		this.effortPerDay = effortPerDay;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	public String getModifiedBy() {
		return modifiedBy;
	}


	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getResourceName() {
		return resourceName;
	}


	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}


	public Long getTeamId() {
		return teamId;
	}

	

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}


	public void setBigintTeamId(BigInteger teamId) {
		this.teamId = teamId.longValue();
	}
	
	@Override
	public String toString() {
		return "ResourcePlan [id=" + id + ", resourceId=" + resourceId + ", projectId=" + projectId + ", teamId="
				+ teamId + ", phase=" + phase + ", startDate=" + startDate + ", endDate=" + endDate + ", totalEffort="
				+ totalEffort + ", effortPerDay=" + effortPerDay + ", effortPercent=" + effortPercent + ", createdDate="
				+ createdDate + ", modifiedDate=" + modifiedDate + ", createdBy=" + createdBy + ", modifiedBy="
				+ modifiedBy + ", comments=" + comments + ", projectName=" + projectName + ", resourceName="
				+ resourceName + "]";
	}
}
