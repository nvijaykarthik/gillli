package in.expedite.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/**
 * Advice to handle the exception in Rest Service.
 * @author vijaykarthik
 *
 */
@ControllerAdvice
public class ExceptionControllerAdvice {

	private static final Logger LOG= LoggerFactory.getLogger(ExceptionControllerAdvice.class.getName());
	private  HttpStatus status=HttpStatus.SERVICE_UNAVAILABLE;

	@Autowired
	ExceptionHandlerMapping exceptionHandlerMapping;
	
	/**
	  * Method to handle exception
	  * @param ex
	  * @return
	  */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExJsonResponse> exceptionHandler(Exception ex) {
    	
    	LOG.info("Capturing Exception");
    	
    	ExJsonResponse error = new ExJsonResponse(); 
    	ResponseEntity<ExJsonResponse> responseEntity=null;
    	Class<? extends Exception> clazz=ex.getClass();
    	ExceptionErrorMapping ee=exceptionHandlerMapping.getExceptionHandler().get(clazz);
    	String msg="";
    	if(clazz.equals(MethodArgumentNotValidException.class)){msg=","+((MethodArgumentNotValidException) ex).getBindingResult().getFieldError().getDefaultMessage();}
    	
    	LOG.error(ex.getMessage(), ex);
    	if(null!=ee && !StringUtils.isEmpty(ee.getCustomMessage())){
    		 LOG.info("Exception Identified : Code is "+ee.getStatus().value()+", Message is:"+ee.getCustomMessage());
    		  error.setCode(ee.getStatus().value());
     		  error.setMessage(ee.getCustomMessage()+msg);  
     		  responseEntity=new ResponseEntity<>(error, ee.getStatus());  
    	}else if(null!=ee && StringUtils.isEmpty(ee.getCustomMessage())){
    		LOG.info("Exception Identified : Code is "+ee.getStatus().value()+", Message is:"+ee.getCustomMessage());
	    	  error.setCode(ee.getStatus().value());	    	  
	   		  error.setMessage(ee.getStatus().name()+msg);  
	   		  responseEntity=new ResponseEntity<>(error, ee.getStatus());  
    	}else{
    		error.setCode(HttpStatus.SERVICE_UNAVAILABLE.value());
	   		error.setMessage(ex.getMessage());  
	   		responseEntity=new ResponseEntity<>(error, HttpStatus.SERVICE_UNAVAILABLE);  
    	}
        return responseEntity;
    }
}
