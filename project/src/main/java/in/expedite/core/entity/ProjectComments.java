package in.expedite.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

@Entity
public class ProjectComments {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="projCmtSeq")
	@SequenceGenerator(name="projCmtSeq",allocationSize=1,initialValue=100)
	private Long id;
	
	@Column
	private Long projectId;
	
	@Lob
	@Column
	private String comment;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
