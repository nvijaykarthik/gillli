package in.expedite.core.service;

import java.util.Date;
import java.util.List;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.Project;
import in.expedite.core.entity.ProjectComments;
import in.expedite.core.entity.ProjectDocType;
import in.expedite.core.entity.ProjectDocuments;
import in.expedite.core.entity.ProjectStatus;
import in.expedite.core.entity.ProjectType;
import in.expedite.core.repository.ProjectCommentsRepository;
import in.expedite.core.repository.ProjectDocTypeRepository;
import in.expedite.core.repository.ProjectDocumentsRepository;
import in.expedite.core.repository.ProjectRepository;
import in.expedite.core.repository.ProjectStatusRepository;
import in.expedite.core.repository.ProjectTypeRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectTypeRepository projectTypeRepository;
	
	@Autowired
	private ProjectDocTypeRepository projectDocTypeRepository;
	
	@Autowired
	private ProjectStatusRepository projectStatusRepository;
	
	@Autowired
	private ProjectRepository projectRepository; 
	
	@Autowired
	private ProjectDocumentsRepository projectDocumentsRepository;
	
	@Autowired
	private ProjectCommentsRepository projectCommentsRepo;
	
	@Value("${expedite.page.size}")
	private Integer pageSize;
	
	public List<ProjectType> getAllProjectTypes() {
		return projectTypeRepository.findAll();
	}

	public List<ProjectDocType> getAllProjectDocTypes() {
		return projectDocTypeRepository.findAll();
	}
	
	public List<ProjectStatus> getAllProjectStatus() {
		return projectStatusRepository.findAll();
	}
	
	public Project addNewProject(String projectName,String username){
		
		Project project = new Project();
		project.setName(projectName);
		project.setCreatedBy(username);
		project.setModifiedBy(username);
		project.setCreatedDate(new Date());
		project.setModifiedDate(new Date());
		return projectRepository.save(project);
	}
	
	public Project updateProject(Project project,String username){
		project.setModifiedBy(username);
		project.setModifiedDate(new Date());
		return projectRepository.save(project);
	}

	public Project getProjectView(Long id) {
		return projectRepository.findOne(id);
	}
	
	public List<ProjectComments> getAllCommentsForProject(Long projectId){
		 
		List<ProjectComments> comments=projectCommentsRepo.findByProjectId(projectId);
		
		Parser parser = Parser.builder().build();
		
		HtmlRenderer renderer = HtmlRenderer.builder().build();

		comments.forEach(comment->{
			Node document = parser.parse(comment.getComment());
			String html=renderer.render(document); 
			comment.setParsedData(html);
		});
		
		return comments;
	}

	public void addCommentsForProject(ProjectComments projectComments, String username) {
		projectComments.setCreatedDate(new Date());
		projectComments.setCreatedBy(username);
		projectCommentsRepo.save(projectComments);
		
	}
	
	public void updateDocument(ProjectDocuments projDoc,String userName){
		projDoc.setCreatedBy(userName);
		projDoc.setCreatedDate(new Date());
		projectDocumentsRepository.save(projDoc);
	}
	
	public List<ProjectDocuments> getDocumentsForProject(Long projectId){
		return projectDocumentsRepository.findByProjectId(projectId);
	}
	
}
