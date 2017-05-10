package in.expedite.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProjectStatus {

	@Id
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
