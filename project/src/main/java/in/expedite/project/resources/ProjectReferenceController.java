package in.expedite.project.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.project.entity.ReferenceProjects;
import in.expedite.project.service.ReferenceProjectService;
import in.expedite.project.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/projReference")
public class ProjectReferenceController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReferenceProjectService projectService;
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ReferenceProjects addReferenceProject(@RequestBody ReferenceProjects refProj,@RequestAttribute(required=false) String username){
		return projectService.addReferenceProject(refProj,username);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse deleteReferenceProj(@RequestParam Long id){
		projectService.deleteReferenceProj(id);
		return new ExJsonResponse(0, "Successfully deleted");
	}
}
