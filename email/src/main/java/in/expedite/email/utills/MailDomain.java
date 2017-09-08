package in.expedite.email.utills;

import java.util.ArrayList;
import java.util.List;

public class MailDomain {

	private String to;
	private String from;
	private String bcc;
	private String cc;
	private String subject;
	private String body;
	private List<String> toMails= new ArrayList<>();
	
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "MailDomain [to=" + to + ", from=" + from + ", bcc=" + bcc + ", cc=" + cc + ", subject=" + subject
				+ ", body=" + body + ", toMails=" + toMails + "]";
	}
	public List<String> getToMails() {
		return toMails;
	}
	public void setToMails(List<String> toMails) {
		this.toMails = toMails;
	}
}
