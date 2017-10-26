package learn.spring.authorization.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import learn.spring.authorization.dao.SuperUserDaoImpl;
import learn.spring.authorization.model.UserInfo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private SuperUserDaoImpl<UserInfo> userRepo;
	
	private final static Log logger = LogFactory.getLog(MyUserDetailsService.class);
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("HEY HEY HEY HEY HEY HEY HEY HEY HEY HEY HEY HEY Checking for User with username: "+username);
		UserDetails u = userRepo.getUserByUsername(username);
		if(u == null) {
			throw new UsernameNotFoundException("Username "+username+" not found");
		}
		return u;
	}

	
	
}
