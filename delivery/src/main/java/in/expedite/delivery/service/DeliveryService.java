package in.expedite.delivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.delivery.entity.Delivery;
import in.expedite.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	public List<Delivery> getDeliveryForProject(Long projectId){
		List<Delivery> delivery= deliveryRepository.findByProjectId(projectId);
		return delivery;
	}
}
