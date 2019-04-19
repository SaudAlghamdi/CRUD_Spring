package sa.com.saud.crud.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sa.com.saud.crud.repository.UserRepository;
import sa.com.saud.crud.model.CRUDUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CRUDUser user = userRepository.findByUsername(username);	
	

		if (user == null) {
			new UsernameNotFoundException("User Not Found with username : " + username);
		}
		return UserPrinciple.build(user);
	}
	
	
}