package in.expedite.utils;

import org.springframework.http.HttpStatus;
/**
 * This class is handle the exception and custom messages.
 * @author Vijaykarthik n 
 *
 */
public class ExceptionErrorMapping {

	private HttpStatus status;
	private String customMessage;
	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getCustomMessage() {
		return customMessage;
	}
	public void setCustomMessage(String customMessage) {
		this.customMessage = customMessage;
	}
	public ExceptionErrorMapping(HttpStatus status, String customMessage) {
		super();
		this.status = status;
		this.customMessage = customMessage;
	} 
	
}
