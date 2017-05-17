package in.expedite.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import in.expedite.core.service.CommonService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonServiceTest {

	@Autowired
	private CommonService commonService;
	
	@Test
	public void testSaveFileToRepoString() {
		
	}

}
