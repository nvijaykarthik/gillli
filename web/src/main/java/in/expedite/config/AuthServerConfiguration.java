package in.expedite.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;

import in.expedite.utils.ExceptionErrorMapping;
import in.expedite.utils.ExceptionHandlerMapping;

@Configuration
@ImportResource("classpath:spring-config.xml")
public class AuthServerConfiguration {

	@Value("${expedite.message.bad.request}")
	private String badReqError;
	
	@Value("${expedite.message.invalid.request}")
	private String invReqMsg;
	
	@Bean
	public ExceptionHandlerMapping exceptionHandlerMapping(){
		ExceptionHandlerMapping mapping = new ExceptionHandlerMapping();
		Map<Class<? extends Exception>,ExceptionErrorMapping> exceptionHandler = new HashMap<>();
		exceptionHandler.put(HttpMessageNotReadableException.class, new ExceptionErrorMapping(HttpStatus.BAD_REQUEST,invReqMsg));
		mapping.setExceptionHandler(exceptionHandler);	
		return mapping;	
	}
}
