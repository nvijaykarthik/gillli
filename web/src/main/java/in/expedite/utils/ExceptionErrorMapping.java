package in.expedite.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
/**
 * This class is handle the exception and custom messages.
 * @author Vijaykarthik n 
 *
 */
public class ExceptionErrorMapping {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
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
