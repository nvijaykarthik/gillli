package in.expedite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class RoleAccessXref {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="roleAccessSeq")
	@SequenceGenerator(name="roleAccessSeq",allocationSize=1,initialValue=100)
	private Long id;
	
	@Column(nullable=false)
	private String roleCode;
	
	@Column(nullable=false)
	private String accessCode;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Override
	public String toString() {
		return "RoleAccessXref [id=" + id + ", roleCode=" + roleCode + ", accessCode=" + accessCode + "]";
	}

	
}
