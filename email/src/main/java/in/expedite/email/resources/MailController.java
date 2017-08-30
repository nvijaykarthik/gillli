package in.expedite.email.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.expedite.email.service.MailService;
import in.expedite.email.utills.MailDomain;



@RestController
@RequestMapping("/resource/mail")
public class MailController {

	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MailService mailService;
	
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public void sendMail(MailDomain mailData) throws Exception {
		mailService.sendMail(mailData);
	}
}
