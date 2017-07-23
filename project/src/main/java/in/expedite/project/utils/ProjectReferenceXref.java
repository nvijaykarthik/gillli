package in.expedite.project.utils;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectReferenceXref {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private BigInteger id;
	private Long projectId;
	private BigInteger referenceId;
	private String projectName;
	private String status;
	private String depMode;
	
	public ProjectReferenceXref(String depMode) {
		super();
		this.depMode = depMode;
	}
	
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public ProjectReferenceXref(){}
	
	public ProjectReferenceXref(BigInteger id, Long projectId, BigInteger referenceId, String projectName, String status,
			String depMode) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.referenceId = referenceId;
		this.projectName = projectName;
		this.status = status;
		this.depMode=depMode;
	}
	@Override
	public String toString() {
		return "ProjectReferenceXref [id=" + id + ", projectId=" + projectId + ", referenceId=" + referenceId
				+ ", projectName=" + projectName + ", status=" + status + "]";
	}
	

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public BigInteger getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(BigInteger referenceId) {
		this.referenceId = referenceId;
	}

	public String getDepMode() {
		return depMode;
	}

	public void setDepMode(String depMode) {
		this.depMode = depMode;
	}

	
	
	
}
