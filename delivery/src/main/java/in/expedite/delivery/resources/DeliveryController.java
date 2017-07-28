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

import in.expedite.delivery.entity.Delivery;
import in.expedite.delivery.service.DeliveryService;

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
	
	@RequestMapping(value="/uploadArtifacts", method=RequestMethod.POST)
    public String artifactUpload(@RequestParam(name="file",required=false) MultipartFile file,
    		@RequestParam("deliveryId") Long deliveryId,
    		@RequestParam("applicationId") Long applicationId,
    		@RequestParam("version") String version){
		
        if (!file.isEmpty()) {
        	deliveryService.saveArtifactToRepo(file, deliveryId,applicationId,version);
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
	
}
