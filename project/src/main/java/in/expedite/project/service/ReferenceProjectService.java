package in.expedite.project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.expedite.project.entity.ReferenceProjects;
import in.expedite.project.repository.ReferenceProjectRepo;

@Service
public class ReferenceProjectService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReferenceProjectRepo referenceProjectRepo;
	
	public ReferenceProjects addReferenceProject(ReferenceProjects refProj, String username){
		refProj.setCreatedBy(username);
		refProj.setModifiedBy(username);
		
		return referenceProjectRepo.save(refProj);
	}
	
	public void deleteReferenceProj(Long id){
		referenceProjectRepo.delete(id);
	}
}
