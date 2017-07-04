package in.expedite.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.project.entity.Estimates;
import in.expedite.project.entity.ProjectType;
import in.expedite.project.service.EstimationService;

@RestController
@RequestMapping("/resource/estimates")
public class EstimationController {

	@Autowired
	private EstimationService estimationService;
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Estimates> getEstimatesProjTeam(@RequestParam Long projId,@RequestParam Long teamId){
		return estimationService.getEstimatesProjTeam(projId,teamId);
	}
	

	@RequestMapping(path="/project", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Estimates> getEstimatesProj(@RequestParam Long projId){
		return estimationService.getEstimatesProj(projId);
	}
	
	@RequestMapping(path="/team",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Estimates> getEstimatesTeam(@RequestParam Long teamId){
		return estimationService.getEstimatesTeam(teamId);
	}
	
	
}
