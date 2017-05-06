package in.expedite.core.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="deptSeq")
	@SequenceGenerator(name="deptSeq",allocationSize=1,initialValue=100)
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String departmentName;
	
	@Column(nullable=true)
	private Long parentDepartmentId;
	
	@Column(nullable=true)
	private String managerId;
    
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Long getParentDepartmentId() {
		return parentDepartmentId;
	}

	public void setParentDepartmentId(Long parentDepartmentId) {
		this.parentDepartmentId = parentDepartmentId;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	@Override
	public String toString() {
		return "Departments [id=" + id + ", departmentName=" + departmentName + ", parentDepartmentId="
				+ parentDepartmentId + ", managerId=" + managerId + "]";
	}
}
