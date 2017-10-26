package learn.spring.authorization.dao;

import java.util.List;

import learn.spring.authorization.model.BaseModel;

public interface GenericDao<T extends BaseModel> {

	void save(T entity);
	void update(T entity);
	void remove(T entity);
	void removeById(long id);
	T getById(long id);
	List<T> getAllUsers();
	public void setClazz(Class<T> clazz);
		
}
