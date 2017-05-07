package in.expedite.auth.interceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.authenticator.SpnegoAuthenticator.AuthenticateAction;
import org.hamcrest.core.IsInstanceOf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import in.expedite.entity.MyUser;

@Component
public class ReSTEnrichInterceptor extends HandlerInterceptorAdapter  {

	/**
	 * Logging 
	 */
	private static final Logger log = LoggerFactory.getLogger(ReSTEnrichInterceptor.class.getName());
	
	/**
	 * This implementation always returns {@code true}.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
				
		List<GrantedAuthority> authorities=Collections.emptyList();
		Authentication auth;
		try{
			auth=SecurityContextHolder.getContext().getAuthentication();
			//authorities=(List<GrantedAuthority>)auth.getAuthorities();
		}catch (NullPointerException e) {
			log.error("Error while enriching the user details",e);
			throw new Exception(e);
		}
		if(null!=auth){
			Object obj=auth.getPrincipal();
			if(obj instanceof MyUser){
				MyUser myUser= (MyUser) obj ;
				List<String> access=new ArrayList<>();
				request.setAttribute("myUser", myUser);
				request.setAttribute("username", myUser.getUsername());
				authorities.forEach(authority->{
					access.add(authority.getAuthority());
				});
				request.setAttribute("authority", access);
				
				MutableHttpServletRequest servletRequest = new MutableHttpServletRequest(request);
				servletRequest.putHeader("username", myUser.getUsername());
				
			}
		}
		return true;
	}
}
