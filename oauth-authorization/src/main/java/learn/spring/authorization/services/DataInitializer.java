package learn.spring.authorization.services;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import learn.spring.authorization.model.UserInfo;

@Component
public class DataInitializer {

	@Autowired
	private Environment env;
	
	@Autowired
	private EntityManagerFactory emf;
	
	private EntityManager em;
	
	@PostConstruct
	public void init() {
		System.out.println("data.initialize: "+env.getProperty("data.initialize"));
		em = emf.createEntityManager();
		if(env.getProperty("data.initialize").equalsIgnoreCase("true")) {
			addUser("first","pass","role_rs_read");
			addUser("second","2nd","role_rs_write");
			addUser("admin","admin","role_rs_read");
		}
	}

	private void addUser(String username, String password, String role) {
		em.getTransaction().begin();
		em.persist(new UserInfo(username,password,role));
		em.flush();
		em.getTransaction().commit();
		
	}
	
	@PreDestroy
	public void destroy() {
		em.close();
		em = null;
	}
	
}
