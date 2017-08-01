package in.expedite.delivery.resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.multipart.MultipartFile;

import in.expedite.delivery.entity.Artifacts;
import in.expedite.delivery.entity.Delivery;
import in.expedite.delivery.entity.ReviewComment;
import in.expedite.delivery.service.DeliveryService;
import in.expedite.delivery.utills.ExJsonResponse;
import in.expedite.delivery.utills.Status;

@RestController
@RequestMapping("/resource/delivery")
public class DeliveryController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DeliveryService deliveryService;
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public List<Delivery> getDeliveryForProject(@RequestParam Long projectId){
		return deliveryService.getDeliveryForProject(projectId);
	}
	
	
	@RequestMapping(path="/toReview",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public List<Delivery> getReviewingDelivery(){
		return deliveryService.getDeliveryForStatus(Status.SUBMITTED_FOR_APPROVAL.getStatus());
	}
	
	@RequestMapping(value="/uploadArtifacts", method=RequestMethod.POST)
    public String artifactUpload(@RequestParam(name="file",required=false) MultipartFile file,
    		@RequestParam("deliveryId") Long deliveryId,
    		@RequestParam("applicationId") Long applicationId,
    		@RequestParam("version") String version,@RequestAttribute(required=false,name="username") String username){
		
        if (!file.isEmpty()) {
        	deliveryService.saveArtifactToRepo(file, deliveryId,applicationId,version,username);
        }else{
        	throw new RuntimeException("IOError Saving file.: File not found");
        }
        return null;
    }
	
	@RequestMapping(value = "/downloadArtifacts", method = RequestMethod.GET)
	public void artifactDownload(@RequestParam String url,@RequestParam String fileName, HttpServletResponse response) {
	    try {
	       deliveryService.getArtifactFromRepo(url,response,fileName);    
	       response.flushBuffer();
	    } catch (IOException ex) {
	    	log.error("Error writing file to output stream. Filename was '{}'", fileName, ex);
	    	throw new RuntimeException("IOError writing file to output stream");
	    }

	}
	
	@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public Delivery saveDelivery(@RequestBody Delivery delivery,@RequestAttribute(required=false,name="username") String username){
		return deliveryService.saveDelivery(delivery,username);
	}
	
	
	@RequestMapping(path="/getVersion",produces=MediaType.TEXT_PLAIN_VALUE,method=RequestMethod.GET)
	public String getDeliveryVersion(@RequestParam Long appId){
		return deliveryService.getDeliveryVersion(appId);
	}
	
	@RequestMapping(path="/getArtifacts",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public List<Artifacts> getArtifactsByDelivery(@RequestParam Long deliveryId){
		return deliveryService.getArtifactsByDelivery(deliveryId);
	}
	
	@RequestMapping(path="/deleteArtifacts",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.DELETE)
	public ExJsonResponse deleteArtifact(@RequestParam Long id){
		 deliveryService.deleteArtifact(id);
		 return new ExJsonResponse(0,"Successfully deleted");
	}
	
	@RequestMapping(path="/submitForApproval",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public Delivery sendForApproval(@RequestBody Delivery delivery){
		delivery.setStatus(Status.SUBMITTED_FOR_APPROVAL.getStatus());
		 return deliveryService.sendForApproval(delivery);
	}
	
	@RequestMapping(path="/sendForReview",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public Delivery sendForReview(@RequestBody Delivery delivery){
		delivery.setStatus(Status.SENT_FOR_REVIEW.getStatus());
		 return deliveryService.sendForReview(delivery);
	}
	
	@RequestMapping(path="/approve",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public Delivery approve(@RequestBody Delivery delivery){
		delivery.setStatus(Status.APPROVED.getStatus());
		 return deliveryService.approve(delivery);
	}
	
	@RequestMapping(path="/cancel",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public Delivery cancel(@RequestBody Delivery delivery){
		delivery.setStatus(Status.APPROVED.getStatus());
		 return deliveryService.cancel(delivery);
	}
	
	@RequestMapping(path="/addComment",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.POST)
	public ReviewComment addReviewComment(@RequestBody ReviewComment comment,@RequestAttribute(required=false,name="username") String username) {
		return deliveryService.addReviewComment(comment, username);
	}
	
	@RequestMapping(path="/getComment",produces=MediaType.APPLICATION_JSON_VALUE,method=RequestMethod.GET)
	public List<ReviewComment> getCommentsByDelivery(@RequestParam Long deliveryId){
		return deliveryService.getCommentsByDelivery(deliveryId);
	}
}
