package in.expedite.web.resource;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.AccessCode;
import in.expedite.entity.Role;
import in.expedite.entity.RoleAccessXref;
import in.expedite.service.RoleService;
import in.expedite.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/role")
public class RoleController {

	private static final Logger LOG = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	RoleService rs;
	
	@RequestMapping(produces="application/json",method=RequestMethod.GET)
	public List<Role> getRoles(){
		LOG.info("Getting List of Available Roles");
		return rs.getRoles();
	}
	
	@RequestMapping(produces="application/json",method=RequestMethod.POST)
	public ExJsonResponse addRole(@Valid @RequestBody Role role){
		LOG.info("Adding new Role" + role.toString());
		rs.addRole(role);
    	return new ExJsonResponse(0,"Sucessfully Added");
	}
	
	@RequestMapping(produces="application/json",method=RequestMethod.PATCH)
	public ExJsonResponse toggleStatus(@Valid @RequestBody Role role){
		LOG.info("Toggling status" + role);
		rs.toggleStatusRole(role);
    	return new ExJsonResponse(0,"Sucessfully Activated");
	}
	
	@RequestMapping(path="/accessRef",produces="application/json",method=RequestMethod.GET)
	public List<AccessCode> getRoleAccessRef(@Valid @RequestParam String roleCode){
		LOG.info("Getting Access List for the Role" + roleCode);
		return rs.getRoleAccessXref(roleCode);
	}

	@RequestMapping(path="/accessRef",produces="application/json",method=RequestMethod.POST)
	public ExJsonResponse addRoleAccessRef(@Valid @RequestBody RoleAccessXref ref){
		LOG.info("Getting Access List for the Role" + ref);
		rs.addRoleAccess(ref);
		return new ExJsonResponse(0,"Sucessfully Added");
	}
	
	@RequestMapping(path="/accessRef",produces="application/json",method=RequestMethod.DELETE)
	public ExJsonResponse delRoleAccessRef(@Valid @RequestBody RoleAccessXref ref){
		LOG.info("Getting Access List for the Role" + ref);
		rs.deleteRoleAccess(ref); 
		return new ExJsonResponse(0,"Sucessfully Deleted");
	}
}
