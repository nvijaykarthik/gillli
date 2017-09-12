package in.expedite.deployment.entity;

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
public class Deployment implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3709315708623288047L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String description;
	
	@Column
	private String status;
	
	@Column
	private String changeBefore;
	
	@Column
	private String changeAfter;
	
	@Column(nullable=true)
	private Long prevDeplNo;
	
	@Column(nullable=true)
	private Long nextDeplNo;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date startDateTime;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date endDateTime;
	
	@Column(nullable=true)
	private String projectReference;

	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date createdDate=new Date();
	
	@Column
	private String createdBy;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChangeBefore() {
		return changeBefore;
	}

	public void setChangeBefore(String changeBefore) {
		this.changeBefore = changeBefore;
	}

	public String getChangeAfter() {
		return changeAfter;
	}

	public void setChangeAfter(String changeAfter) {
		this.changeAfter = changeAfter;
	}

	public Long getPrevDeplNo() {
		return prevDeplNo;
	}

	public void setPrevDeplNo(Long prevDeplNo) {
		this.prevDeplNo = prevDeplNo;
	}

	public Long getNextDeplNo() {
		return nextDeplNo;
	}

	public void setNextDeplNo(Long nextDeplNo) {
		this.nextDeplNo = nextDeplNo;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}


	public String getProjectReference() {
		return projectReference;
	}

	public void setProjectReference(String projectReference) {
		this.projectReference = projectReference;
	}

	public Date getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
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

	@Override
	public String toString() {
		return "Deployment [id=" + id + ", title=" + title + ",  status=" + status
				+ ", changeBefore=" + changeBefore + ", changeAfter=" + changeAfter + ", prevDeplNo=" + prevDeplNo
				+ ", nextDeplNo=" + nextDeplNo + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime
				+ ", projectReference=" + projectReference + ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ "]";
	}

}
