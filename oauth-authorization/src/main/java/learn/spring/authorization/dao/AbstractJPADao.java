package learn.spring.authorization.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import learn.spring.authorization.model.BaseModel;

@Transactional
public abstract class AbstractJPADao<T extends BaseModel> {

	@Autowired
	private EntityManagerFactory emf;

	protected EntityManager em;

	private Class<T> clazz;

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void save(T entity) {
		initTransaction();
		em.persist(entity);
		closeTransaction();
	}

	public void update(T entity) {
		initTransaction();
		em.merge(entity);
		closeTransaction();
	}

	public void remove(T entity) {
		initTransaction();
		em.remove(entity);
		closeTransaction();
	}

	public void removeById(long id) {
		initTransaction();
		String query = "DELETE From " + this.clazz.getName() + " clas where clas.id=" + id;
		em.createQuery(query).executeUpdate();
		closeTransaction();
	}

	public T getById(long id) {
		initTransaction();
		T entity = em.find(this.clazz, id);
		closeTransaction();
		return entity;
	}

	public List<T> getAllUsers() {
		initTransaction();
		@SuppressWarnings("unchecked")
		List<T> entities = em.createQuery("From " + clazz.getName()).getResultList();
		closeTransaction();
		return entities;
	}

	protected void initTransaction() {
		if (em == null) {
			em = emf.createEntityManager();
		}
		em.getTransaction().begin();
	}

	protected void closeTransaction() {
		em.flush();
		em.getTransaction().commit();
	}

}
