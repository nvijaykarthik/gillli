package in.expedite.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Application implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable=false)
	private String appName;
	
	@Column(nullable=false)
	private String appReleaseTag;
	
	@Column(nullable=false)
	private String appDescription;
	
	@Column(nullable=true)
	private String appInstallInstruction;
	
	@Column(nullable=false)
	private Long teamId;
	
	@Column(nullable=true)
	private String applicationScore;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppReleaseTag() {
		return appReleaseTag;
	}

	public void setAppReleaseTag(String appReleaseTag) {
		this.appReleaseTag = appReleaseTag;
	}

	public String getAppDescription() {
		return appDescription;
	}

	public void setAppDescription(String appDescription) {
		this.appDescription = appDescription;
	}

	public String getAppInstallInstruction() {
		return appInstallInstruction;
	}

	public void setAppInstallInstruction(String appInstallInstruction) {
		this.appInstallInstruction = appInstallInstruction;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getApplicationScore() {
		return applicationScore;
	}

	public void setApplicationScore(String applicationScore) {
		this.applicationScore = applicationScore;
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

	@Override
	public String toString() {
		return "Application [id=" + id + ", appName=" + appName + ", appReleaseTag=" + appReleaseTag + ", teamId="
				+ teamId + ", applicationScore=" + applicationScore + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}
	
}
