package in.expedite.utils;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Pojo class to map the exception with handler
 * @author vijaykarthik
 *
 */
public class ExceptionHandlerMapping {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	private Map<Class<? extends Exception>,ExceptionErrorMapping> exceptionHandler;

	public Map<Class<? extends Exception>, ExceptionErrorMapping> getExceptionHandler() {
		return exceptionHandler;
	}

	public void setExceptionHandler(Map<Class<? extends Exception>, ExceptionErrorMapping> exceptionHandler) {
		this.exceptionHandler = exceptionHandler;
	}
	
	
}
