package in.expedite.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="userRoleSeq")
	@SequenceGenerator(name="userRoleSeq",allocationSize=1,initialValue=100)
	private Long id;
	
	@Column
	private String userId;
	
	@Column
	private String roleCode;
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userId=" + userId + ", roleCode=" + roleCode + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
