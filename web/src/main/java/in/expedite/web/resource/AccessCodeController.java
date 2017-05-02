package in.expedite.web.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.AccessCode;
import in.expedite.service.AccessCodeService;

@RestController
@RequestMapping("/resource/accessCode")
public class AccessCodeController {

	@Autowired
	AccessCodeService acs;
	
	private static final Logger LOG = LoggerFactory.getLogger(AccessCodeController.class);
	
	/**
	 * Rest service to get all the available access codes 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public List<AccessCode> getAccessCodes(){
		LOG.info("Retrieving Available access Codes ");
		return acs.getAccessCodes();
	}
	
	
	/**
	 * Rest Service to add new Access Code
	 * @param accessCode
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public AccessCode addAccess(@Valid @RequestBody AccessCode accessCode){
		LOG.info("Adding Access Code:"+accessCode);
		return acs.addAccessCode(accessCode);
	}
	
	/**
	 * Deleting the access codes
	 * @param accessCode
	 */
	@RequestMapping(method=RequestMethod.DELETE,produces="application/json")
	public void deleteAccessCode(AccessCode accessCode){
		LOG.info("Deleting the access code: "+accessCode);
		acs.deleteAccessCode(accessCode);
	}
}
