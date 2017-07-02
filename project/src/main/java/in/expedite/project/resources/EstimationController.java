package in.expedite.project.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.project.service.EstimationService;

@RestController
@RequestMapping("/resource/estimates")
public class EstimationController {

	@Autowired
	private EstimationService estimationService;
	
	
}
