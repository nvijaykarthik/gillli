package in.expedite.core.web.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.core.entity.ProjectDocType;
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
}
