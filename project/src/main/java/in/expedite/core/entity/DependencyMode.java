package in.expedite.core.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DependencyMode {

	@Id
	private String dependency;

	public String getDependency() {
		return dependency;
	}

	public void setDependency(String dependency) {
		this.dependency = dependency;
	}

	
	
}
