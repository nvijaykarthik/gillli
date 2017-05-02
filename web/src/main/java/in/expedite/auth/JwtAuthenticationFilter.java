package in.expedite.auth;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
/**
 * Extending the spring authentication processing filter to extract the jwt to user.
 * @author vijaykarthik
 *
 */
public class JwtAuthenticationFilter extends AbstractAuthenticationProcessingFilter{
	
     private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
  
	 public JwtAuthenticationFilter() {
	        super("/**");
	       	    }

	    @Override
	    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
	        return true;
	    }

	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

	        String header = request.getHeader("Authorization");
	        
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	        	 for (Cookie cookie : cookies) {
	        	   if (cookie.getName().equals("Authorization")) {
	        		   try {
						header=URLDecoder.decode(cookie.getValue(), "UTF-8");
					} catch (UnsupportedEncodingException e) {
						LOG.error("Error Reading Authorization Cookie ",e);
					}
	        		   LOG.info("Retrieved Token from cookie");
	        	   }
	        	 }
	        }
	        
	        if (header == null || !header.startsWith("Bearer ")) {
	            LOG.error("No JWT token found in request headers");
	            throw new AuthorizationServiceException("No JWT token found in request headers");
	        }

	        String authToken = header.substring(7);

	        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

	        return getAuthenticationManager().authenticate(authRequest);
	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
	            throws IOException, ServletException {
	        super.successfulAuthentication(request, response, chain, authResult);

	        // As this authentication is in HTTP header, after success we need to continue the request normally
	        // and return the response as if the resource was not secured at all
	        chain.doFilter(request, response);
	    }

	
}
