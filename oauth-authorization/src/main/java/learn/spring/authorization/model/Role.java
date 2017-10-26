package learn.spring.authorization.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6010650028003428170L;
	private String role;
	
	public Role(String role) {
		this.role = role.toUpperCase();
	}

	public String getRole() {
		return role;
	}

	public String getAuthority() {
		return this.role;
	}
	
	
	
}
