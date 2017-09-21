package in.expedite.deployment.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.deployment.entity.Deployment;
import in.expedite.deployment.service.DeploymentService;

@RestController
@RequestMapping("/resource/deployment")
public class DeploymentController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DeploymentService deploymentService;

	@RequestMapping(path="/search",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Deployment> findDeployments(@RequestParam(required = false) Long id,
			@RequestParam(required = false) String title, @RequestParam(required = false) String status,
			@RequestParam(required = false) String projectReference, @RequestParam(required = false) String createdBy,
			@RequestParam(required = false) Long teamId) throws Exception {
		
		return deploymentService.findDeployments(id, title, status, projectReference, createdBy, teamId);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public Deployment getDeploymentById(@RequestParam Long id) {
		return deploymentService.getDeploymentById(id);
	}
}
