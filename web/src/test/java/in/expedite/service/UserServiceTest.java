package in.expedite.service;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import in.expedite.entity.State;
import in.expedite.entity.User;

@Ignore 
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	
	private static final Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	UserService userService;
	
	
	public void searchUser(){
		userService.findUserByName("vijay").forEach(usr->{
			log.info(usr.toString());
		});
		
	}
	
	@Test
	public void addUser() {
		for(int i=1;i<=30;i++){
			User user = new User();
			user.setEmail("Team.member."+i+"@team.com");
			user.setFirstName("Team");
			user.setUserId("teamMember"+i);
			user.setSecondName("Member"+i);
			user.setState(State.ACTIVE.getState());
			userService.addUser(user);
		}
	}
}
