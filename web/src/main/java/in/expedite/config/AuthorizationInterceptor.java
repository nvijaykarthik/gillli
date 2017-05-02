package in.expedite.config;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import in.expedite.service.AccessCodeService;


@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter { 
	
	@Autowired
	AccessCodeService accService;
	/**
	 * Logging 
	 */
	private static final Logger log = LoggerFactory.getLogger(AuthorizationInterceptor.class.getName());
	
	/**
	 * This implementation always returns {@code true}.
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		List<GrantedAuthority> authorities=Collections.emptyList();
		try{
			authorities=(List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		}catch (NullPointerException e) {
			return true;
		}
		String currentUrl=request.getRequestURI();
		String currentMethod=request.getMethod();
		String accessCode=accService.getAccessCodeForMappings(currentUrl, currentMethod);
		log.debug("Logged in user authorities in Interceptors ["+authorities+"] and URI is ["+currentUrl+"("+currentMethod+")], from DB Access: "+accessCode);
		for(GrantedAuthority authority:authorities){
			if(authority.getAuthority().equals(accessCode)){
				log.debug("You are authorised to access this url ["+currentUrl+"]");
				return true;
			}
		}
		log.debug("You are Not authorised to access this url ["+currentUrl+"]");
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		return true;
	}

}
