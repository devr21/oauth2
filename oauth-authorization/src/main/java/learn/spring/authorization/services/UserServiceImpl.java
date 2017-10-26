package learn.spring.authorization.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import learn.spring.authorization.dao.GenericDao;
import learn.spring.authorization.model.BaseModel;
import learn.spring.authorization.model.UserInfo;

@Service
public class UserServiceImpl {

	private GenericDao<UserInfo> userDao;

	@Autowired
	@Qualifier("baseJPADao")
	public void setDao(GenericDao<UserInfo> userDao) {
		this.userDao = userDao;
		this.userDao.setClazz(UserInfo.class);
	}
	
	public void saveUser(UserInfo user) {
			userDao.save(user);
	}
	
	public void update(UserInfo user) {
		userDao.update(user);
	}
	public void deleteById(long id) {
		userDao.removeById(id);
	}
	public void delete(UserInfo user) {
		userDao.remove(user);
	}
	
	public List<UserInfo> getAllUsers(){
		return userDao.getAllUsers();
	}
	public BaseModel getUserById(long id) {
		return userDao.getById(id);
	}
	
}
