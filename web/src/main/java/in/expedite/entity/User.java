package in.expedite.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author vijayakarthik
 * DB entity class for User Table.
 */

@Entity
@Table(name="USER")
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(nullable=false)
	@NotBlank(message="User Id should not be Empty")
	@Pattern(regexp="^[a-zA-Z0-9]*$",message="In Valid User ID")
	private String userId;
	
	@Column(nullable=false)
	@NotBlank(message="First Name should not be Empty")
	@Pattern(regexp="^[a-zA-Z0-9]*$",message="In Valid First Name")
	private String firstName;
	
	@Column(nullable=false)
	@NotBlank(message="Second Name should not be Empty")
	@Pattern(regexp="^[a-zA-Z0-9]*$",message="In Valid Second Name")
	private String secondName;
	
	@Column(nullable=false,unique=true)
	@NotBlank(message="Email should not be Empty")
	@Email(message="Invalid Email")
	private String email;
	
	@Column(nullable=false)
	@NotBlank(message="Password should not be Empty")
	private String password;

	@NotEmpty
	@Column(name="STATE", nullable=false)
	private String state=State.ACTIVE.getState();
	
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date createdDate=new Date();
	
	@Column
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")  
	private Date modifiedDate=new Date();
	
	@Column
	private String createdBy;
	
	@Column
	private String modifiedBy;
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", secondName=" + secondName + ", email=" + email
				+ ", password=" + password + ", state=" + state + ", createdDate=" + createdDate + ", modifiedDate="
				+ modifiedDate + "]";
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}

	