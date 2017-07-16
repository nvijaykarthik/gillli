package in.expedite.project.resources;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.project.entity.Estimates;
import in.expedite.project.entity.ProjectType;
import in.expedite.project.service.EstimationService;
import in.expedite.project.utils.ConsolidatedEstimate;
import in.expedite.project.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/estimates")
public class EstimationController {

	@Autowired
	private EstimationService estimationService;
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Estimates> getEstimatesProjTeam(@RequestParam Long projId,@RequestParam Long teamId){
		return estimationService.getEstimatesProjTeam(projId,teamId);
	}
	

	@RequestMapping(method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse deleteEstimate(@RequestParam Long estiId){
		estimationService.deleteEstimate(estiId);
		return new ExJsonResponse(0, "Successfully Deleted");
	}
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public Estimates addEstimate(@RequestBody Estimates esti,@RequestAttribute(required=false) String username){
		
		return estimationService.addEstimate(esti,username);
	}
	
	@RequestMapping(path="/project", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Estimates> getEstimatesProj(@RequestParam Long projId){
		return estimationService.getEstimatesProj(projId);
	}
	
	@RequestMapping(path="/team",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Estimates> getEstimatesTeam(@RequestParam Long teamId){
		return estimationService.getEstimatesTeam(teamId);
	}
	
	@RequestMapping(path="/project/consolidated", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<ConsolidatedEstimate>  getEstimatesConsProj(@RequestParam Long projId){
		return estimationService.getConsEstimatesProj(projId);
	}
	
}
