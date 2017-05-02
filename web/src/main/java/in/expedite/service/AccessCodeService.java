package in.expedite.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import in.expedite.entity.AccessCode;
import in.expedite.repository.AccessCodeRepository;

/**
 * Access code service which has getall, add and delete operation, there is no update. 
 * @author vijaykarthik N 
 *
 */
@Service
@CacheConfig(cacheNames = "accessCode")
public class AccessCodeService {

	@Autowired
	AccessCodeRepository acRepo;
	
	private static final Logger LOG = LoggerFactory.getLogger(AccessCodeService.class);
	
	/**
	 * Adding new ACCESS CODE WITH MENU LINK 
	 * @param accessCode
	 * @return
	 */
	public AccessCode addAccessCode(AccessCode accessCode){
		LOG.debug("Adding new Access Code: "+accessCode);
		return acRepo.save(accessCode);
	}
	
	/**
	 * Deleting the access code.
	 * @param accessCode
	 */
	public void deleteAccessCode(AccessCode accessCode){
		LOG.debug("Removing the access code: "+ accessCode);
		acRepo.delete(accessCode);
	}
	
	public List<AccessCode> getAccessCodes(){
		LOG.debug("Retrieving all the Access codes available");
		 List<AccessCode> ac=acRepo.findAll();
		LOG.trace("Access codes : "+ac);
		return ac;
	}
	
	@Cacheable
	public String getAccessCodeForMappings(String mapping,String method){
		LOG.debug("Retrieving Access codes for "+mapping+" on "+ method);
		AccessCode accessCode=acRepo.findByMappingAndMethod(mapping, method);
		if(null==accessCode){
			return null;
		}
		return accessCode.getAccessCode();
	}
}
