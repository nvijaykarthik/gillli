package in.expedite.auth;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger log = Logger.getLogger(JwtAuthenticationSuccessHandler.class.getName());
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		log.fine("Rest Service No Redirection required, so leave as it is");
	}

}
