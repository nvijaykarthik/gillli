package in.expedite.web.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.service.UserService;
import in.expedite.utils.ExJsonResponse;

@RestController
@RequestMapping("/openresource")
public class PasswordMgmtController {


	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/forgotpassword",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ExJsonResponse forgotPassword(@RequestParam String loginId) throws Exception{
		userService.forgotpassword(loginId);
		return new ExJsonResponse(0, "Reset link sent successfully");
	}
	
	@RequestMapping(path="/verifyToken",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String verifyToken(@RequestParam String resetId) throws Exception{
		return userService.verifyToken(resetId);
	}
	
	@RequestMapping(path="/changePwd",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String changePwd() throws Exception{
		return null;
	}
}
