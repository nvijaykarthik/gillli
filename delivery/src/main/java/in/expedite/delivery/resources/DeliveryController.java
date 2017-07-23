package in.expedite.delivery.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
