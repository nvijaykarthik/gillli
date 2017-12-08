package in.expedite.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.Application;
import in.expedite.core.repository.ApplicationRepository;

@Service		
public class ApplicationService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public List<Application> getApplicationForTeam(Long teamId){
		log.debug("Getting Application for team id : {}",teamId);
		return applicationRepository.findByTeamId(teamId);
	}

	public Application save(Application app, String username) {
			app.setCreatedBy(username);
			app.setModifiedBy(username);
			app.setCreatedDate(new Date());
			app.setModifiedDate(new Date());
			log.debug("Saving the application {}", app);
			
			return applicationRepository.save(app);
	}

	public List<Application> findAppl(String q) {
		List<Application> appLst=new ArrayList<>();
		log.debug("Search Query is  {}",q);
		if(StringUtils.isBlank(q)) {
			log.debug("Getting all the Applications with max 25 data order by created date");
			Pageable page= new PageRequest(0, 25,Sort.Direction.DESC,"createdDate");
			appLst=applicationRepository.findAll(page).getContent();
		}else {
			log.debug("Getting the Application for the query {}",q);
			appLst=applicationRepository.findByAppNameIgnoreCaseContaining(q);
		}
		return appLst;
	}
}
