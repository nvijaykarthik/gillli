package in.expedite.deployment.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Task implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8404571531293899522L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String name;
	
	@Lob
	@Column
	private String details;
	@Column
	private String status;
	@Column
	private String owner;
	@Column
	private Integer sequence;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date startDateTime;
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date endDateTime;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date createdDate=new Date();
	
	@Column
	private String createdBy;

	@Column
	private Long depolymentId;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Date getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
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
		return "Task [id=" + id + ", name=" + name + ", details=" + details + ", status=" + status + ", owner=" + owner
				+ ", sequence=" + sequence + ", startDateTime=" + startDateTime + ", endDateTime=" + endDateTime
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", depolymentId=" + depolymentId + "]";
	}

	public Long getDepolymentId() {
		return depolymentId;
	}

	public void setDepolymentId(Long depolymentId) {
		this.depolymentId = depolymentId;
	}
}
