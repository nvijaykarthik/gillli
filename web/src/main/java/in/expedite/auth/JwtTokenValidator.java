package in.expedite.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import in.expedite.entity.MyUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenValidator {

	private static final Logger LOG = LoggerFactory.getLogger(JwtTokenValidator.class);
	
	@Value("${jwt.secret}")
    private String secret;
    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public MyUser parseToken(String token) {
        MyUser currentUser = null;
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            
            @SuppressWarnings("unchecked")
			List<Map<String,String>> authorityList = (List<Map<String,String>>)body.get("role");
            currentUser = new MyUser(body.getSubject(),"nondisclosed",(boolean)body.get("enabled"),
            		(boolean)body.get("accountNonExpired"),(boolean)body.get("credentialsNonExpired"),
            		(boolean)body.get("accountNonLocked"),wrapperGrantedAuthorities(authorityList),
            		(String)body.get("firstName"),(String)body.get("lastName"),(String)body.get("email"));
            
        } catch (JwtException e) {
            // Simply print the exception and null will be returned for the userDto
        	LOG.error("Error while parsing", e);
        }
        return currentUser;
    }
    
    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     * 
     * @param u the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(MyUser u) {
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("name", u.getFirstName());
        claims.put("role", u.getAuthorities());
        claims.put("email", u.getEmail());
        claims.put("firstName", u.getFirstName());
        claims.put("lastName", u.getLastName());
        claims.put("accountNonExpired", u.isAccountNonExpired());
        claims.put("accountNonLocked", u.isAccountNonLocked());
        claims.put("enabled", u.isEnabled());
        claims.put("credentialsNonExpired", u.isCredentialsNonExpired());
        
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
    
    private static List<GrantedAuthority> wrapperGrantedAuthorities(List<Map<String,String>> authorityList){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        authorityList.forEach(map->{
        	authorities.add(new SimpleGrantedAuthority(map.get("authority")));
        });
                 
      //  }
       // System.out.print("authorities :"+authorities);
        return authorities;
    }
    
}
