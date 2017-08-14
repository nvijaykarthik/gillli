package in.expedite.core.resources;

import java.util.List;

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

import in.expedite.core.entity.Application;
import in.expedite.core.service.ApplicationService;

@RestController
@RequestMapping("/resource/application")
public class ApplicationController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationService applicationService;
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Application> getApplicationForTeam(@RequestParam Long teamId){
		return applicationService.getApplicationForTeam(teamId);
	}
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Application saveApplication(@RequestBody Application app,@RequestAttribute(required=false) String username){
		return applicationService.save(app,username);
	}
	
	@RequestMapping(path="/find",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Application> getApplication(@RequestParam(required=false) String q){
		return applicationService.findAppl(q);
	}
	
}
