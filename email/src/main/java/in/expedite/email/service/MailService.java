package in.expedite.email.service;

import java.util.Arrays;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import in.expedite.email.utills.MailDomain;


@Service
public class MailService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private JavaMailSender mailSender;
	
	
	public void sendMail(MailDomain domain) throws Exception{
    	log.info("Starting Send...");	
    	MimeMessage msg = mailSender.createMimeMessage();
    	try{
    		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true /* multipart */, "UTF-8");
    		String[] mails=domain.getTo().split(",");
    		List<String> mailList=Arrays.asList(mails);
    		mailList.addAll(domain.getToMails());
    	    mimeMessageHelper.setTo((String[])mailList.toArray());
    	    mimeMessageHelper.setFrom(domain.getFrom());
    	    mimeMessageHelper.setSubject(domain.getSubject());
    	    mimeMessageHelper.setText(domain.getBody(),true);            
            this.mailSender.send(msg);
        }
        catch(MailException | MessagingException ex){
            log.error("Error sending mail",ex);
        }
        log.info("Finished Send...");
    }

}
