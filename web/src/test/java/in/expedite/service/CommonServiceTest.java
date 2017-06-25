package in.expedite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import in.expedite.core.service.TeamServices;
import in.expedite.project.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonServiceTest {

	@Autowired
	private ProjectService projService;
	
	@Autowired
	TeamServices teamService;
	
	@Test
	public void test() {
		teamService.getTeamsForMembers("vkarthik");
	}

}
