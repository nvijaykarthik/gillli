package in.expedite.resourcePlan.resource;

import java.util.Date;

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

import in.expedite.resourcePlan.entity.ResourcePlan;
import in.expedite.resourcePlan.service.ResourcePlanService;
import in.expedite.resourcePlan.utills.ExJsonResponse;
import in.expedite.resourcePlan.utills.ResourcePlanData;

@RestController
@RequestMapping("/resource/resourcePlan")
public class ResourcePlanController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ResourcePlanService resourcePlanService;
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResourcePlanData getPlan(@RequestParam Long teamId,@RequestParam Integer pgNo){
		return resourcePlanService.getPlannedResource(teamId, pgNo);
	}
	
	@RequestMapping(method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResourcePlan savePlan(@RequestBody ResourcePlan rp,@RequestAttribute(required=false,name="username") String username){
		return resourcePlanService.savePlan(rp,username);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public  ExJsonResponse deletePlan(@RequestParam Long planId){
		resourcePlanService.deletePlan(planId);
		return new ExJsonResponse(0,"Deleted Successfully");
	}
	
}
