package learn.spring.authorization.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import learn.spring.authorization.model.BaseModel;
import learn.spring.authorization.model.UserInfo;

@SuppressWarnings("hiding")
@Repository
@Transactional
public class SuperUserDaoImpl<UserInfo> extends AbstractJPADao<BaseModel>{
	
	@SuppressWarnings("unchecked")
	public UserInfo getUserByUsername(String user) {
		initTransaction();
		List<UserInfo> users = em.createQuery("From UserInfo where username=:user").setParameter("user", user).getResultList();
		closeTransaction();
		if(users.isEmpty())
			return null;
		return users.iterator().next();
	}

}
