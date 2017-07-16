package in.expedite.project.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import in.expedite.project.entity.DependencyMode;
import in.expedite.project.entity.Estimates;
import in.expedite.project.entity.Project;
import in.expedite.project.entity.ProjectComments;
import in.expedite.project.entity.ProjectDocType;
import in.expedite.project.entity.ProjectDocuments;
import in.expedite.project.entity.ProjectStatus;
import in.expedite.project.entity.ProjectType;
import in.expedite.project.entity.ReferenceProjects;
import in.expedite.project.repository.DependencyModeRepo;
import in.expedite.project.repository.EstimatesRepository;
import in.expedite.project.repository.ProjectCommentsRepository;
import in.expedite.project.repository.ProjectDocTypeRepository;
import in.expedite.project.repository.ProjectDocumentsRepository;
import in.expedite.project.repository.ProjectReferenceDao;
import in.expedite.project.repository.ProjectRepository;
import in.expedite.project.repository.ProjectStatusRepository;
import in.expedite.project.repository.ProjectTypeRepository;
import in.expedite.project.repository.ReferenceProjectRepo;
import in.expedite.project.utils.ProjectReferenceXref;

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
	
	@Autowired
	private DependencyModeRepo dependencyModeRepo;
	
	@Autowired
	private EstimatesRepository estimatesRepo;
	
	@Autowired
	private ProjectReferenceDao projrefdao;
	
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

	public List<Project> searchProject(String query) {
		return projectRepository.searchProject(query.toLowerCase());
	}
	
	public List<Project> searchPrograms(String query) {
		return projectRepository.searchPrograms(query.toLowerCase());
	}
	
	public List<Project> getPrograms() {
		return projectRepository.findByTypeIgnoreCase("Program");
	}
	
	public List<Project> getProjectByProgram(Long programId){
		return projectRepository.findByProgramId(programId);
	}
	
	public List<DependencyMode> getDependencymodes(){
		return dependencyModeRepo.findAll();
	}
	
	public List<ProjectReferenceXref> getReferenceProjects(Long projectId){
		return projrefdao.getProjectReferences(projectId);
	}

	public List<Project> getAvailProject() {
		List<String> status=new ArrayList<String>();
		status.add("Went Live");
		status.add("Cancelled");
		status.add("Rejected");
		status.add("Completed");
		return  projectRepository.findByStatusNotInIgnoreCase(status);
	}

	public List<Project> getMyProject(Long teamId, String status) {
		//Estimated
		
		List<Estimates> esti=estimatesRepo.findByTeamId(teamId);
		Set<Long> projId=new HashSet<>();
		esti.forEach(est->{
			projId.add(est.getProjectId());
		});
		if(StringUtils.isBlank(status))
			status="New";
		
		//Planned
		//Delivered
		return projectRepository.findByIdInAndStatusIgnoreCase(projId, status);
	
	}
}
