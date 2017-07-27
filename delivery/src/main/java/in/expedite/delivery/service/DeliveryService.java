package in.expedite.delivery.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import in.expedite.delivery.dao.DeliveryDao;
import in.expedite.delivery.entity.Delivery;
import in.expedite.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DeliveryRepository deliveryRepository;
	
	@Autowired
	private DeliveryDao deliveryDao;
	
	public List<Delivery> getDeliveryForProject(Long projectId){
		List<Delivery> delivery= deliveryDao.getDeliveryForProject(projectId);
		return delivery;
	}

	public void saveArtifactToRepo(MultipartFile file, String string) {
		// TODO Auto-generated method stub
		
	}
}
