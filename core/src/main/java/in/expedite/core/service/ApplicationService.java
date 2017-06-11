package in.expedite.core.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.core.entity.Application;
import in.expedite.core.repository.ApplicationRepository;

@Service		
public class ApplicationService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	public List<Application> getApplicationForTeam(Long teamId){
		return applicationRepository.findByTeamId(teamId);
	}

	public Application save(Application app, String username) {
			app.setCreatedBy(username);
			app.setModifiedBy(username);
			app.setCreatedDate(new Date());
			app.setModifiedDate(new Date());
			return applicationRepository.save(app);
	}
}
