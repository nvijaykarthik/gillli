package in.expedite.core.repository;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.expedite.core.utils.ProjectReferenceXref;

@Repository
public class ProjectReferenceDao {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ReferenceProjectRepo referenceProjectRepo;
	
	public List<ProjectReferenceXref> getProjectReferences(Long projectId){
		List<Object[]> xrefObj=referenceProjectRepo.findRefForProject(projectId);
		List<ProjectReferenceXref> xrefList=new ArrayList<>() ;
		xrefObj.forEach(ob->{
			ProjectReferenceXref prx=new ProjectReferenceXref((BigInteger)ob[0], projectId, (BigInteger)ob[1], (String)ob[2], (String)ob[3],(String)ob[4]);
			xrefList.add(prx);
		});
	
		log.debug("result : "+xrefList);
		
		return xrefList;
	}
}
