package learn.spring.authorization.model;

import java.io.Serializable;

import org.springframework.security.core.userdetails.UserDetails;

public interface BaseModel extends Serializable,UserDetails{
	
	public long getId();
	
}
