package sa.com.saud.crud.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
/*import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter; */
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import sa.com.saud.crud.security.jwt.JwtAuthEntryPoint;
import sa.com.saud.crud.security.jwt.JwtAuthTokenFilter;
import sa.com.saud.crud.security.service.UserDetailsServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
    prePostEnabled = true
)public class SecurityConfiguration extends WebSecurityConfigurerAdapter{ 
	
    @Autowired
    UserDetailsServiceImpl userDetailsService;
 
    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;
 

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }
 
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
 
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        
        http.addFilterBefore((Filter) authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
	
	
	
}
	
	//extends WebSecurityConfigurerAdapter {

	
	/*.addFilter(getDigestAuthFilter()).exceptionHandling()
	.authenticationEntryPoint(getDigestEntryPoint())
	.and().authorizeRequests().anyRequest().authenticated();*/
		/*@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests().antMatchers("/login","/register").permitAll()
			.anyRequest().authenticated().and().formLogin();
			

		}
	
		private DigestAuthenticationFilter getDigestAuthFilter () throws Exception {
			
			DigestAuthenticationFilter dFilter = new DigestAuthenticationFilter();
			dFilter.setUserDetailsService(userDetailsService());
			dFilter.setAuthenticationEntryPoint(getDigestEntryPoint());
			dFilter.setCreateAuthenticatedToken(true);
			return dFilter;
			
		}
	
		private DigestAuthenticationEntryPoint getDigestEntryPoint () {
			DigestAuthenticationEntryPoint ep = new DigestAuthenticationEntryPoint();
			
			ep.setRealmName("admin-digest-realm");
			ep.setKey("sldkwplk_+");
			
			return ep;
		}
		
		@Override
		@Bean
		protected UserDetailsService userDetailsService() {
			return super.userDetailsService();
		}
		
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
			
		}
		
		public PasswordEncoder getPasswordEncoder () {
			
			DelegatingPasswordEncoder encoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
			encoder.setDefaultPasswordEncoderForMatches(new MessageDigestPasswordEncoder("SHA-256"));
			return encoder;
		}
		
	
*/
