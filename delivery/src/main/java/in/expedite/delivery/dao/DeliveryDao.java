package in.expedite.delivery.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
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
		
		List<Object[]> objects =em.createNativeQuery("select d.application_id,d.change_description,d.created_date,"
				+ "d.project_id,d.team_id,"
				+ "d.release_tag,d.version,concat(u.first_name, ' ' , u.second_name) as userName,"
				+ "a.app_name,p.name ,d.created_by,d.id,t.team_name,d.status "
				+ "from delivery d,"
				+ "user u,"
				+ "application a,"
				+ "project p,"
				+ "team t"
				+ " where "
				+ "u.user_id=d.created_by "
				+ "and a.id=d.application_id "
				+ "and p.id=d.project_id "
				+ "and t.id=d.team_id "
				+ "and d.project_id=:projectId")
				.setParameter("projectId", projectId)
				.getResultList();
		
		objects.forEach( obj->{
			
			Delivery delivery = new Delivery();
			delivery.setBigIntApplicationId((BigInteger)obj[0]);
			delivery.setApplicationName((String)obj[8]);
			delivery.setChangeDescription((String) obj[1]);
			delivery.setCreatedBy((String)obj[10]);
			delivery.setBigIntId((BigInteger)obj[11]);
			delivery.setBigIntProjectId((BigInteger)obj[3]);
			delivery.setProjectName((String)obj[9]);
			delivery.setReleaseTag((String)obj[5]);
			delivery.setBigIntTeamId((BigInteger)obj[4]);
			delivery.setTeamName((String)obj[12]);
			delivery.setUserId((String)obj[10]);
			delivery.setUserName((String)obj[7]);
			delivery.setVersion((String) obj[6]);
			delivery.setCreatedDate((Date)obj[2]);
			delivery.setStatus((String)obj[13]);
			del.add(delivery);
			
		});
		
		return del;
	}
	
	
public List<Delivery> getDeliveryForStatus(String status){
		
		List<Delivery> del=new ArrayList<Delivery>();
		
		List<Object[]> objects =em.createNativeQuery("select d.application_id,d.change_description,d.created_date,"
				+ "d.project_id,d.team_id,"
				+ "d.release_tag,d.version,concat(u.first_name, ' ' , u.second_name) as userName,"
				+ "a.app_name,p.name ,d.created_by,d.id,t.team_name,d.status "
				+ "from delivery d,"
				+ "user u,"
				+ "application a,"
				+ "project p,"
				+ "team t"
				+ " where "
				+ "u.user_id=d.created_by "
				+ "and a.id=d.application_id "
				+ "and p.id=d.project_id "
				+ "and t.id=d.team_id "
				+ "and d.status=:status")
				.setParameter("status", status)
				.getResultList();
		
		objects.forEach( obj->{
			
			Delivery delivery = new Delivery();
			delivery.setBigIntApplicationId((BigInteger)obj[0]);
			delivery.setApplicationName((String)obj[8]);
			delivery.setChangeDescription((String) obj[1]);
			delivery.setCreatedBy((String)obj[10]);
			delivery.setBigIntId((BigInteger)obj[11]);
			delivery.setBigIntProjectId((BigInteger)obj[3]);
			delivery.setProjectName((String)obj[9]);
			delivery.setReleaseTag((String)obj[5]);
			delivery.setBigIntTeamId((BigInteger)obj[4]);
			delivery.setTeamName((String)obj[12]);
			delivery.setUserId((String)obj[10]);
			delivery.setUserName((String)obj[7]);
			delivery.setVersion((String) obj[6]);
			delivery.setCreatedDate((Date)obj[2]);
			delivery.setStatus((String)obj[13]);
			del.add(delivery);
			
		});
		
		return del;
	}


public List<Delivery> getDelivery(String status,
								Long teamId,
								String releaseTag,
								Long applicationId,
								Long projectId){
	
	List<Delivery> del=new ArrayList<Delivery>();
	StringBuilder sb = new StringBuilder(
			"select d.application_id,d.change_description,d.created_date,"
			+ "d.project_id,d.team_id,"
			+ "d.release_tag,d.version,concat(u.first_name, ' ' , u.second_name) as userName,"
			+ "a.app_name,p.name ,d.created_by,d.id,t.team_name,d.status "
			+ "from delivery d,"
			+ "user u,"
			+ "application a,"
			+ "project p,"
			+ "team t"
			+ " where "
			+ "u.user_id=d.created_by "
			+ "and a.id=d.application_id "
			+ "and p.id=d.project_id "
			+ "and t.id=d.team_id "
			+ "and d.status=:status");
	if(null!=teamId)
		sb.append(" and d.team_id="+teamId);
	
	if(null!=applicationId)
		sb.append(" and d.application_id="+applicationId);
	
	if(null!=projectId)
		sb.append(" and d.project_id="+projectId);
	
	if(StringUtils.isNotBlank(releaseTag))
		sb.append(" and d.release_tag='"+releaseTag+"'");
	
	sb.append(" order by created_date desc");
	
	Query query=em.createNativeQuery(sb.toString());
	query.setParameter("status", status);
	List<Object[]> objects =query.getResultList();
	
	objects.forEach( obj->{
		Delivery delivery = new Delivery();
		delivery.setBigIntApplicationId((BigInteger)obj[0]);
		delivery.setApplicationName((String)obj[8]);
		delivery.setChangeDescription((String) obj[1]);
		delivery.setCreatedBy((String)obj[10]);
		delivery.setBigIntId((BigInteger)obj[11]);
		delivery.setBigIntProjectId((BigInteger)obj[3]);
		delivery.setProjectName((String)obj[9]);
		delivery.setReleaseTag((String)obj[5]);
		delivery.setBigIntTeamId((BigInteger)obj[4]);
		delivery.setTeamName((String)obj[12]);
		delivery.setUserId((String)obj[10]);
		delivery.setUserName((String)obj[7]);
		delivery.setVersion((String) obj[6]);
		delivery.setCreatedDate((Date)obj[2]);
		delivery.setStatus((String)obj[13]);
		del.add(delivery);
		
	});
	
	return del;
}
}
