package in.expedite.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.project.entity.DependencyMode;
import in.expedite.project.entity.Project;
import in.expedite.project.entity.ProjectComments;
import in.expedite.project.entity.ProjectDocType;
import in.expedite.project.entity.ProjectDocuments;
import in.expedite.project.entity.ProjectStatus;
import in.expedite.project.entity.ProjectType;
import in.expedite.project.service.ProjectService;
import in.expedite.project.utils.ProjectReferenceXref;


@RestController
@RequestMapping("/resource/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(path="/projectType",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectType> getProjectType(){
		return projectService.getAllProjectTypes();
	}
	
	@RequestMapping(path="/projectDocType",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectDocType> getProjectDocType(){
		return projectService.getAllProjectDocTypes();
	}
	
	@RequestMapping(path="/projectStatus",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectStatus> getProjectStatus(){
		return projectService.getAllProjectStatus();
	}
	
	@RequestMapping(path="/newProject",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Project addNewProject(@RequestBody String projectName,@RequestAttribute(required=false) String username ){
		return projectService.addNewProject(projectName, username);
	}
	
	@RequestMapping(path="/updateProject",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Project updateProject(@RequestBody Project project,@RequestAttribute(required=false) String username ){
		return projectService.updateProject(project, username);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Project getProjectView(@RequestParam Long id){
		return projectService.getProjectView(id);
	}
	
	
	@RequestMapping(path="/comments",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectComments> getProjectComments(@RequestParam Long projectId){
		return projectService.getAllCommentsForProject(projectId);
	}
	
	
	@RequestMapping(path="/comments",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public void addProjectComments(@RequestBody ProjectComments projectComments,@RequestAttribute(required=false) String username){
		projectService.addCommentsForProject(projectComments,username);
	}
	
	@RequestMapping(path="/updateDocument",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateDocument(@RequestBody ProjectDocuments projectDocuments,@RequestAttribute(required=false) String username){
		projectService.updateDocument(projectDocuments,username);

	}
	@RequestMapping(path="/documentsList",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectDocuments> getProjectDocuments(@RequestParam Long projectId){
		return projectService.getDocumentsForProject(projectId);
	}
	
	
	@RequestMapping(path="/search",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Project> searchProject(@RequestParam String query){
		return projectService.searchProject(query);
	}
	
	@RequestMapping(path="/programs",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getPrograms(){
		return projectService.getPrograms();
	}
	
	@RequestMapping(path="/projectsForProgram",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getProjectsForProgram(@RequestParam Long programId){
		return projectService.getProjectByProgram(programId);
	}
	
	@RequestMapping(path="/dependencyModes",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<DependencyMode> getDependencyMode(){
		return projectService.getDependencymodes();
	}
	
	@RequestMapping(path="/referenceProjects",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ProjectReferenceXref> getReferenceProjects(@RequestParam Long projectId){
		return projectService.getReferenceProjects(projectId);
	}
	
	@RequestMapping(path="/availProject",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getAvailProject(){
		return projectService.getAvailProject();
	}
	
	@RequestMapping(path="/myProject",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Project> getMyProject(@RequestParam Long teamId,@RequestParam(required=false) String status){
		return projectService.getMyProject(teamId,status);
	}
	
	
	
}
