package in.expedite.resourcePlan.repository;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.expedite.resourcePlan.entity.ResourcePlan;
import in.expedite.resourcePlan.utills.ResourcePlanData;

@Repository
public class ResourcePlanDao {

	private static final Logger log = LoggerFactory.getLogger(ResourcePlanDao.class);
	
	
	@Autowired
	private EntityManager em;
	
	
	public ResourcePlanData getPlan(Long teamId,Date startDate,Date endDate,ResourcePlanData rpd) {
		
		final DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		log.debug(teamId +" " +startDate +" "+format.format(endDate) + " "+rpd);
		List<Object[]> objects =em.createNativeQuery(""
				+ "SELECT rp.id,rp.resource_Id,rp.project_id,rp.team_id,rp.phase,"
				+ "rp.start_Date,rp.end_Date,rp.total_Effort,rp.effort_Per_Day,rp.comments,"
				+ "t.team_name,u.first_name,u.second_name,p.name "
				+ "FROM Resource_Plan rp,Team t, User u,Project p where "
				+ "t.id=rp.team_Id "
				+ "and u.user_Id=rp.resource_Id "
				+ "and rp.project_Id=p.id "
				+ "and rp.end_date>=:startDate and rp.start_date<=:endDate "
				+ "and t.id=:teamId")
				.setParameter("teamId",teamId)
				.setParameter("startDate", startDate)
				.setParameter("endDate", format.format(endDate))
				.getResultList();
					
		objects.forEach( obj->{
			ResourcePlan rp=new ResourcePlan();
			
			rp.setBigintId((BigInteger)obj[0]); 
			rp.setResourceId((String)obj[1]);
			rp.setBigintProjectId((BigInteger)obj[2]);
			rp.setBigintTeamId((BigInteger)obj[3]);
			rp.setPhase((String)obj[4]);
			rp.setStartDate((Date) obj[5]);
			rp.setEndDate((Date) obj[6]);
			rp.setTotalEffort((Integer) obj[7]);
			rp.setEffortPerDay((Integer) obj[8]);
			rp.setComments((String) obj[9]);
			rp.setResourceName((String) obj[11] +" "+(String) obj[12]);
			rp.setProjectName((String) obj[13]);
			rpd.getData().add(rp);
			log.debug("Adding Data "+rp);
		});
		
		return rpd;
	}

}
