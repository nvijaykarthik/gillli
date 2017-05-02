package in.expedite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class AccessCode {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	
	@NotBlank(message="Method should not be Empty")
	@Pattern(regexp="^[a-zA-Z0-9_]*$",message="inValid Method")
	@Column(nullable=false)
	private String method;
	@Column(nullable=false)
	private String mapping;
	@Column(nullable=false,unique=true)
	private String accessCode;
	@Column(nullable=false)
	private String accessDesc;
	
	@Transient
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getAccessDesc() {
		return accessDesc;
	}

	public void setAccessDesc(String accessDesc) {
		this.accessDesc = accessDesc;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getMapping() {
		return mapping;
	}

	public void setMapping(String mapping) {
		this.mapping = mapping;
	}

	@Override
	public String toString() {
		return "AccessCode [id=" + id + ", method=" + method + ", mapping=" + mapping + ", accessCode=" + accessCode
				+ ", accessDesc=" + accessDesc + ", active=" + active + "]";
	}

	
}
