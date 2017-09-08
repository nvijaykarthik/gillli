package in.expedite.email.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import in.expedite.email.utills.MailDomain;

@Service
public class PasswordManagmentService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private MailService mailservice;
	
	@Autowired
	Configuration freeMakerConfig;
	
	
	@Value("${gillli.email.from}")
	private String fromAddress;
	
	@Async
	public void sendForgotPasswordMail(String resetLink,String userEmail) throws Exception {
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("resetLink",resetLink);
		String text=FreeMarkerTemplateUtils.processTemplateIntoString(freeMakerConfig.getTemplate("velocity/resetPassword.html"),model);
		
		MailDomain domain = new MailDomain();
		domain.setTo(userEmail);
		domain.setSubject("Gillli : Reset Your Password");
		domain.setBody(text);
		domain.setFrom(fromAddress);
		mailservice.sendMail(domain);
	}
}
