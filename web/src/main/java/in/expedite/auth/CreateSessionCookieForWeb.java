package in.expedite.auth;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import in.expedite.entity.MyUser;
/**
 * Custom auth success handler to create cookie to handle ReST .  
 * @author vijaykarthik
 *
 */
@Component
public class CreateSessionCookieForWeb extends SimpleUrlAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private JwtTokenValidator jwtTokenValidator;
	
	private static final Logger LOG = LoggerFactory.getLogger(CreateSessionCookieForWeb.class); 
	//private RequestCache requestCache = new HttpSessionRequestCache();
	/**
	 * Override the existing function to add jwt as cookie
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		MyUser currentUser=(MyUser) authentication.getPrincipal();
		LOG.info("Creating JWT cookie");
		final String cookieName = "Authorization";
	    final String cookieValue = "Bearer " + jwtTokenValidator.generateToken(currentUser);  // you could assign it some encoded value
	    final Boolean useSecureCookie = new Boolean(false);
	    final int expiryTime = 60 * 60 * 24;  // 24h in seconds
	    final String cookiePath = "/";

	    Cookie myCookie = new Cookie(cookieName, URLEncoder.encode(cookieValue, "UTF-8"));
	    myCookie.setSecure(useSecureCookie.booleanValue());  // determines whether the cookie should only be sent using a secure protocol, such as HTTPS or SSL
	    myCookie.setMaxAge(-1);  // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
	    myCookie.setPath(cookiePath);  // The cookie is visible to all the pages in the directory you specify, and all the pages in that directory's subdirectories
	    
	    response.addCookie(myCookie);
	    
		super.onAuthenticationSuccess(request, response, authentication);
		return;
	    
	}

}
