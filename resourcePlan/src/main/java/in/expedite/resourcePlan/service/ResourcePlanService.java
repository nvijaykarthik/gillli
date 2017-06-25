package in.expedite.resourcePlan.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import in.expedite.resourcePlan.entity.ResourcePlan;
import in.expedite.resourcePlan.repository.ResourcePlanDao;
import in.expedite.resourcePlan.repository.ResourcePlanRepo;
import in.expedite.resourcePlan.utills.ResourcePlanData;

@Service
public class ResourcePlanService {

	@Autowired
	private ResourcePlanRepo resourcePlanRepo;
	
	@Autowired
	private ResourcePlanDao resourcePlanDao;
	
	@Value("${gilli.cell.count}")
	private Integer cellSize;
	
	public ResourcePlanData getPlannedResource(Long teamId,Integer pgNo){
		ResourcePlanData rpd = new ResourcePlanData();
		
		rpd.setTotalCell(cellSize);
		Integer startDtCnt=cellSize*pgNo;
		Date currDate=new Date();
		LocalDateTime localDateTime = currDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		if(startDtCnt<0){
			localDateTime = localDateTime.minusDays(startDtCnt);
		}else if(startDtCnt>0){
			localDateTime = localDateTime.plusDays(startDtCnt);
		}else if(startDtCnt==0){
			localDateTime = localDateTime.minusDays(3L);
		}

		Date currentDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		localDateTime = localDateTime.plusDays(cellSize.longValue()+3L);
		Date endDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		rpd.setCurrentDate(currentDate);
		
		rpd=resourcePlanDao.getPlan(teamId,currentDate,endDate,rpd);
		
		rpd.getData().sort((ResourcePlan o1, ResourcePlan o2) -> {
			return o1.getResourceName().compareTo(o2.getResourceName());
		});
		
		return rpd;
	}
	
	public ResourcePlan savePlan(ResourcePlan rp,String username){
		rp.setModifiedBy(username);
		rp.setModifiedDate(new Date());
		return resourcePlanRepo.save(rp);
	}
	
	public void deletePlan(Long planId){
		resourcePlanRepo.delete(planId);
	}
}
