package in.expedite.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import in.expedite.entity.MyUser;

/**
 * Extending authentication provider to parse the jwt token
 * 
 * @author vijaykarthik
 *
 */

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationProvider.class);
	
	@Autowired
	private JwtTokenValidator jwtTokenValidator;

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		
		LOG.debug("Authenticating ReST");
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
		String token = jwtAuthenticationToken.getToken();
		
		MyUser parsedUser = jwtTokenValidator.parseToken(token);
		
		if (parsedUser == null) {
			LOG.error("JWT token is not valid");
		}
		
		//List<GrantedAuthority> authorityList = (List<GrantedAuthority>) parsedUser.getAuthorities();
		LOG.debug("Logged in User details:"+parsedUser);
		return parsedUser;
	}
}
