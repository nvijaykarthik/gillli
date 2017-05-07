package in.expedite.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

public class ProjectDocuments {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="projDocSeq")
	@SequenceGenerator(name="projDocSeq",allocationSize=1,initialValue=100)
	private Long id;
	
	@Column
	private Long projectId;
	
	@Column
	private String title;
	
	@Column
	private String type;
	
	@Column
	private String content;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
