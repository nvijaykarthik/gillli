package in.expedite.core.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.core.entity.Project;
import in.expedite.core.entity.ProjectComments;
import in.expedite.core.entity.ProjectDocType;
import in.expedite.core.entity.ProjectDocuments;
import in.expedite.core.entity.ProjectStatus;
import in.expedite.core.entity.ProjectType;
import in.expedite.core.service.ProjectService;


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
	
}
