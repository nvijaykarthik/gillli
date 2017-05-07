package in.expedite.web.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.entity.MyUser;
import in.expedite.entity.Role;
import in.expedite.entity.State;
import in.expedite.entity.User;
import in.expedite.entity.UserRole;
import in.expedite.service.UserService;
import in.expedite.utils.ExJsonResponse;

@RestController
@RequestMapping("/resource/users")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@RequestMapping(method=RequestMethod.GET,path="/principal",produces="application/json")
	public MyUser getAuthUser(@RequestAttribute("myUser") MyUser myUser) {
		return myUser;
	}
	
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json",path="/searchByName")
	public Iterable<User> getUsersbyName(@RequestParam("s") String name){
		return userService.findUserByName(name);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public Iterable<User> getUsers(@RequestParam("p") Integer pageNo,
			@RequestParam(name="userId",required=false) String userId,
			@RequestParam(name="firstName",required=false) String firstName,
			@RequestParam(name="lastName",required=false) String secondName,
			@RequestParam(name="email",required=false) String email,
			@RequestParam(name="state",required=false) String state){
		return userService.searchUser(userId, firstName, secondName, email, state, pageNo);
	}
	
	@RequestMapping(path="/states",method=RequestMethod.GET,produces="application/json")
	public List<String> getState(){
		List<String> states=new ArrayList<>();
		for(State state:State.values()){
			states.add(state.getState());
		}
		return states ;
	}
	
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public ExJsonResponse saveUser(@RequestBody User user){
		userService.addUser(user);
		return new ExJsonResponse(0,"Sucessfully Added");
	}
	
	@RequestMapping(path="/update",method=RequestMethod.POST,produces="application/json")
	public ExJsonResponse updateUser(@RequestBody User user,@RequestHeader(required=false) String username){
		userService.updateUser(user,username);
		return new ExJsonResponse(0,"Sucessfully Updated");
	}
	
	@RequestMapping(path="/updatePwd",method=RequestMethod.GET,produces="application/json")
	public ExJsonResponse updatePwd(@RequestParam String userId,@RequestParam String password){
		userService.updatePassword(userId, password,userId);
		return new ExJsonResponse(0,"Sucessfully Updated");
	}
	
	@RequestMapping(path="/resetPwd",method=RequestMethod.GET,produces="application/json")
	public ExJsonResponse resetUserPwd(@RequestParam String userId){
		userService.resetPassword(userId,userId);
		return new ExJsonResponse(0,"Password Reset Sucessfull");
	}
	
	@RequestMapping(path="/addRolesToUser",method=RequestMethod.POST,produces="application/json")
	public ExJsonResponse addRoleToUser(@RequestBody UserRole userRole,@RequestHeader(required=false) String username){
		userService.addUserRole(userRole,username);
		return new ExJsonResponse(0,"Roles Configured Successfully");
	}
	
	@RequestMapping(path="/deleteRoleFromUser",method=RequestMethod.DELETE,produces="application/json")
	public ExJsonResponse deleteRoleFromUser(@RequestBody UserRole userRole){
		userService.deleteUserRole(userRole.getUserId(),userRole.getRoleCode());
		return new ExJsonResponse(0,"Roles Configured Successfully");
	}
	
	@RequestMapping(path="/getRolesForUser",produces="application/json",method=RequestMethod.GET)
	public List<Role> getRolesForUser(@Valid @RequestParam String userId){
		return userService.getActiveRolesForUser(userId);
	}
	
	@RequestMapping(path="/manager",method=RequestMethod.GET,produces="application/json")
	public User getManager(@RequestParam String managerId){
    	return userService.getUser(managerId);
	}

	@RequestMapping(path="/members",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public List<User> getMembersForTeam(@RequestParam Long teamId){
		return userService.getMembersForTeam(teamId);
	}
	
}
