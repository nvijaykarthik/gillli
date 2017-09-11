package in.expedite.email.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import in.expedite.email.DAO.EmailSupportDAO;
import in.expedite.email.utills.Constants;
import in.expedite.email.utills.MailDomain;

@Service
public class DeliveryMailService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmailSupportDAO configurationDao;

	private String getEmailIdsOfDelivery() {

		return configurationDao.getValue(Constants.CM_EMAIL_KEY);
	}

	@Autowired
	private MailService mailservice;

	@Autowired
	Configuration freeMakerConfig;

	@Value("${gillli.email.from}")
	private String fromAddress;

	@Async
	public void sendMailForApprovalRequest(String reqId, String project, String team, String application,
			String version, String tag, String changeDesc, String status,Long teamId) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("requestId", reqId);
		model.put("project", project);
		model.put("team", team);
		model.put("application", application);
		model.put("version", version);
		model.put("tag", tag);
		model.put("changeDesc", changeDesc);
		model.put("status", status);

		String text = FreeMarkerTemplateUtils
				.processTemplateIntoString(freeMakerConfig.getTemplate("velocity/deliveryApprovalRequest.html"), model);

		MailDomain domain = new MailDomain();
		String toEmailIds = getEmailIdsOfDelivery();
		List<String> emails=configurationDao.getTeamEmail(teamId);
		emails.addAll(Arrays.asList(toEmailIds.split(",")));
		domain.setTo(emails);
		domain.setSubject("Gillli : Delivery Approval Request : " + reqId);
		domain.setBody(text);
		domain.setFrom(fromAddress);
		mailservice.sendMail(domain);
	}

	@Async
	public void sendMailForRejection(String reqId, String project, String team, String application,
			String version, String tag, String changeDesc, String status,Long teamId,String rejectComment) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("requestId", reqId);
		model.put("project", project);
		model.put("team", team);
		model.put("application", application);
		model.put("version", version);
		model.put("tag", tag);
		model.put("changeDesc", changeDesc);
		model.put("status", status);
		model.put("rejMsg", rejectComment);

		String text = FreeMarkerTemplateUtils
				.processTemplateIntoString(freeMakerConfig.getTemplate("velocity/deliveryReject.html"), model);

		MailDomain domain = new MailDomain();
		String toEmailIds = getEmailIdsOfDelivery();
		List<String> emails=configurationDao.getTeamEmail(teamId);
		emails.addAll(Arrays.asList(toEmailIds.split(",")));
		domain.setTo(emails);
		domain.setSubject("Gillli : Delivery Sent for Review Request : " + reqId);
		domain.setBody(text);
		domain.setFrom(fromAddress);
		mailservice.sendMail(domain);
	}
	
	@Async
	public void sendMailForApproved(String reqId, String project, String team, String application,
			String version, String tag, String changeDesc, String status,Long teamId,String apprComment) throws Exception {

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("requestId", reqId);
		model.put("project", project);
		model.put("team", team);
		model.put("application", application);
		model.put("version", version);
		model.put("tag", tag);
		model.put("changeDesc", changeDesc);
		model.put("status", status);
		model.put("apprMsg", apprComment);

		String text = FreeMarkerTemplateUtils
				.processTemplateIntoString(freeMakerConfig.getTemplate("velocity/deliveryApprove.html"), model);

		MailDomain domain = new MailDomain();
		String toEmailIds = getEmailIdsOfDelivery();
		List<String> emails=configurationDao.getTeamEmail(teamId);
		emails.addAll(Arrays.asList(toEmailIds.split(",")));
		domain.setTo(emails);
		domain.setSubject("Gillli : Delivery Sent for Review Request : " + reqId);
		domain.setBody(text);
		domain.setFrom(fromAddress);
		mailservice.sendMail(domain);
	}
}
