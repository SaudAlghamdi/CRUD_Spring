package sa.com.saud.crud.security.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sa.com.saud.crud.model.CRUDUser;
import sa.com.saud.crud.repository.UserRepository;
import sa.com.saud.crud.request.LoginBody;
import sa.com.saud.crud.security.jwt.JwtProvider;
import sa.com.saud.crud.security.response.JwtResponse;
import sa.com.saud.crud.security.response.ResponseMessage;
import saud.com.crud.Constants.SecurityConstants;

/**
 * @author salghamdi
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

	  @Autowired
	  AuthenticationManager authenticationManager;
	 
	  @Autowired
	  UserRepository userRepository;
	 	 
	  @Autowired
	  PasswordEncoder encoder;
	 
	  @Autowired
	  JwtProvider jwtProvider;
	  
	  
	  @PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser( @RequestBody LoginBody loginRequest) {
	 
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	 
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	 
	    String jwt = jwtProvider.generateJwtToken(authentication);
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	 
	    return ResponseEntity.ok(new JwtResponse(jwt, SecurityConstants.FIVE_MINUTE_IN_MILLIS ,userDetails.getUsername(), userDetails.getAuthorities()));
	  }
	 
	  
	  @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@RequestBody CRUDUser signUpRequest) {
		  
	    if (userRepository.findByUsername(signUpRequest.getUsername()) != null) {
	      return new ResponseEntity<>(new ResponseMessage("Username is already taken!"),
	          HttpStatus.BAD_REQUEST);
	    }
	 
	    // Creating user's account
	    CRUDUser user = new CRUDUser();
	    user.setUsername(signUpRequest.getUsername());
	    user.setPassword(encoder.encode(signUpRequest.getPassword()));
	    user.setRole(signUpRequest.getRole());
	    user.setEnabled(signUpRequest.getEnabled());
	 
	    userRepository.save(user);
	 
	    return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
	  }
	  
	  
	  @PostMapping("/valdiate")
	  public  ResponseEntity<?> validateToken (@RequestBody String token){
		  
		 boolean isAuthenticated = jwtProvider.validateJwtToken(token);
		  
		 if (isAuthenticated) {
		    return new ResponseEntity<>(new ResponseMessage("true"), HttpStatus.OK);
		 } else {
			 
		      return new ResponseEntity<>(new ResponseMessage("false"),
			          HttpStatus.BAD_REQUEST);
		 }
		  
	  }
	  
	  
	
}
