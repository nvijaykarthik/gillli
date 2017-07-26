package in.expedite.delivery.entity;

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

import in.expedite.delivery.utills.Status;

@Entity
public class Delivery implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2062647515356081463L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	public void setBigIntId(BigInteger id){
		this.id=id.longValue();
	}
	
	@Column
	private Long teamId;
	
	public void setBigIntTeamId(BigInteger teamId){
		this.teamId=teamId.longValue();
	}
	
	@Column
	private Long projectId;
	
	public void setBigIntProjectId(BigInteger projectId){
		this.projectId=projectId.longValue();
	}
	
	@Column 
	private String userId;
	
	@Column
	private Long applicationId;
	
	public void setBigIntApplicationId(BigInteger applicationId){
		this.applicationId=applicationId.longValue();
	}
	
	@Column
	private String version;
	
	@Column
	private String releaseTag;
	
	@Column
	private String changeDescription;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date createdDate=new Date();
	
	@Column
	private String createdBy;
	
	@Column
	private String status=Status.NEW.getStatus();
	
	@Transient
	private String teamName;
	
	@Transient
	private String projectName;
	
	@Transient
	private String userName;
	

	@Transient
	private String applicationName;
	
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

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getReleaseTag() {
		return releaseTag;
	}

	public void setReleaseTag(String releaseTag) {
		this.releaseTag = releaseTag;
	}

	public String getChangeDescription() {
		return changeDescription;
	}

	public void setChangeDescription(String changeDescription) {
		this.changeDescription = changeDescription;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	
	@Override
	public String toString() {
		return "Delivery [id=" + id + ", teamId=" + teamId + ", projectId=" + projectId + ", userId=" + userId
				+ ", applicationId=" + applicationId + ", version=" + version + ", releaseTag=" + releaseTag
				+ ", changeDescription=" + changeDescription + ", createdDate=" + createdDate + ", createdBy="
				+ createdBy + ", teamName=" + teamName + ", projectName=" + projectName + ", userName=" + userName
				+ ", applicationName=" + applicationName + "]";
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
