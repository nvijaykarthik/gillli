package in.expedite.delivery.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Artifacts implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7572173934741577184L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String artifactName;
	
	@Column
	private String artifactUrl;
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date createdDate=new Date();
	
	@Column
	private String createdBy;
	
	@Column
	private Long deliveryId;
	
	
	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getArtifactName() {
		return artifactName;
	}

	public void setArtifactName(String artifactName) {
		this.artifactName = artifactName;
	}

	public String getArtifactUrl() {
		return artifactUrl;
	}

	public void setArtifactUrl(String artifactUrl) {
		this.artifactUrl = artifactUrl;
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


	

}
