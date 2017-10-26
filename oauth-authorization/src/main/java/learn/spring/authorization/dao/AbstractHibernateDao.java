package learn.spring.authorization.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import learn.spring.authorization.model.BaseModel;

public abstract class AbstractHibernateDao<T extends BaseModel> implements GenericDao<T>{

	/*@Autowired*/
	private SessionFactory sessionFactory;
	
	protected Session session;
	
	private Set<Transaction> trxns;
	
	public void openSession() {
		if(session == null) {
			synchronized(session){
				if(session == null)
					session = sessionFactory.openSession();
			}
		}
		Transaction tx = session.getTransaction();
		tx.begin();
		trxns.add(tx);
	}
	
	public void closeSession() {
		Transaction tx = session.getTransaction();
		tx.commit();
		trxns.remove(tx);
		if(trxns.isEmpty()) {
			session.close();
			session = null;
		}
	}
	
}
