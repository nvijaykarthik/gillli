package in.expedite.auth;

import java.util.logging.Logger;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(JwtAuthenticationToken.class.getName());

	private String token;
    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }
    public String getToken() {
    	log.finest(token);
        return token;
    }
    @Override
    public Object getCredentials() {
        return null;
    }
    @Override
    public Object getPrincipal() {
        return null;
    }
}
