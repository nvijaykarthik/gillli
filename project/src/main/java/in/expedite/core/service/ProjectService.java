package in.expedite.core.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.Project;
import in.expedite.core.entity.ProjectDocType;
import in.expedite.core.entity.ProjectStatus;
import in.expedite.core.entity.ProjectType;
import in.expedite.core.repository.ProjectDocTypeRepository;
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
}
