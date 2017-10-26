package learn.spring.authorization.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import learn.spring.authorization.model.BaseModel;

@Repository
@Scope( BeanDefinition.SCOPE_PROTOTYPE)
public class BaseJPADao<T extends BaseModel> extends AbstractJPADao<T> implements GenericDao<T>{
	
}
