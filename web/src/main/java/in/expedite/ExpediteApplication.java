package in.expedite;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import in.expedite.auth.CreateSessionCookieForWeb;
import in.expedite.auth.FailureHandler;
import in.expedite.auth.JwtAuthenticationFilter;
import in.expedite.auth.JwtAuthenticationProvider;
import in.expedite.auth.JwtAuthenticationSuccessHandler;
import in.expedite.auth.RestAuthenticationEntryPoint;
import in.expedite.service.UserDetailsService;

@SpringBootApplication
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
public class ExpediteApplication extends SpringBootServletInitializer  {


	
	public static void main(String[] args) {
		SpringApplication.run(ExpediteApplication.class, args);
	}
	
	@Component
	protected class CacheManagerCheck implements CommandLineRunner {

		private final Logger logger = LoggerFactory.getLogger(CacheManagerCheck.class);

		private final CacheManager cacheManager;

		public CacheManagerCheck(CacheManager cacheManager) {
			this.cacheManager = cacheManager;
		}

		@Override
		public void run(String... strings) throws Exception {
			logger.info("\n\n" + "=========================================================\n"
					+ "Using cache manager: " + this.cacheManager.getClass().getName() + "\n"
					+ "=========================================================\n\n");
		}
	}
	
   @Configuration
   protected static class AuthServer extends WebMvcConfigurerAdapter{
	
	  @Autowired 
	  HandlerInterceptor authorizationInterceptor;
	  
	 @Override
	 public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	 }
	
	 
	 @Override
	  public void addInterceptors(InterceptorRegistry registry) {
		 registry.addInterceptor(authorizationInterceptor);
	 }
   }
   
	@Configuration
	protected static class LoginConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		UserDetailsService userDetailsService;

		@Autowired
		CreateSessionCookieForWeb createSessionCookieForWeb;
		
		
		@Autowired
		FailureHandler failureHandler;
		@Override
		protected void configure(HttpSecurity http) throws Exception {
		
			http
				.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/index.html", true)
				.successHandler(createSessionCookieForWeb)
				.failureHandler(failureHandler)
				.permitAll()
			.and()
				.authorizeRequests()
				.antMatchers("/login","/pages/**").permitAll()
				.anyRequest().authenticated()
			.and()
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
	
		}

		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			 auth.userDetailsService(userDetailsService).passwordEncoder(bcryptEncoder());
		}
		
		@Bean
		public PasswordEncoder bcryptEncoder(){
			return new BCryptPasswordEncoder();
		}
		
	}
	
	@Configuration
	@Order(-1)
	protected static class RestAuthConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		
		@Autowired
		JwtAuthenticationProvider jwtAuthenticationProvider;
		
		@Autowired
		JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler;
		
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
			   .antMatcher("/resource/**")
			   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			   .and()
			   .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
			   .and()
			   .addFilterAfter(jwtAuthenticationFilter(),BasicAuthenticationFilter.class).csrf().disable();
			   
		}

		@Bean
		@Override
		public AuthenticationManager authenticationManagerBean() throws Exception {
		      return super.authenticationManagerBean();
		}
		
		@Autowired
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(jwtAuthenticationProvider);
		}
		
		protected Filter jwtAuthenticationFilter() throws Exception {   
	        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
	        jwtAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler);
	        jwtAuthenticationFilter.setAuthenticationManager(this.authenticationManagerBean());
	        return jwtAuthenticationFilter;
		}
	}

}

