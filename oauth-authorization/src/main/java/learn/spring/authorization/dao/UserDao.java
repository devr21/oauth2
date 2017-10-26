package learn.spring.authorization.dao;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import learn.spring.authorization.model.UserInfo;

public interface UserDao{

	UserDetails getUserByUsername(String user);
	void save(UserInfo user);
	void update(UserInfo user);
	void deleteById(long id);
	void delete(UserInfo user);
	List<UserInfo> getAllUsers();
	UserInfo getUserById(long id);
	
}
