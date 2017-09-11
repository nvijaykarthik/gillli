package in.expedite.email.utills;

import java.util.ArrayList;
import java.util.List;

public class MailDomain {

	private List<String> to;
	private String from;
	private List<String> bcc;
	private List<String> cc;
	private String subject;
	private String body;
	
	public List<String> getTo() {
		return to;
	}
	public void setTo(List<String> to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public List<String> getBcc() {
		return bcc;
	}
	public void setBcc(List<String> bcc) {
		this.bcc = bcc;
	}
	public List<String> getCc() {
		return cc;
	}
	public void setCc(List<String> cc) {
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
				+ ", body=" + body + "]";
	}
	
	
	
}
