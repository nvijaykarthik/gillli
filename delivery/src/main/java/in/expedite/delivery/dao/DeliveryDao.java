package in.expedite.delivery.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.expedite.delivery.entity.Delivery;

@Repository
public class DeliveryDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EntityManager em;
	
	public List<Delivery> getDeliveryForProject(Long projectId){
		
		List<Delivery> del=new ArrayList<Delivery>();
		
		List<Object[]> objects =em.createNativeQuery("")
				.setParameter("projectId", projectId)
				.getResultList();
		
		objects.forEach(obj->{
			
		});
		
		return null;
	}
}
