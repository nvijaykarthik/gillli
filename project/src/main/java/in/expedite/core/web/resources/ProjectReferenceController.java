package in.expedite.core.web.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.core.entity.ReferenceProjects;
import in.expedite.core.service.ReferenceProjectService;
import in.expedite.core.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/projReference")
public class ProjectReferenceController {

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
