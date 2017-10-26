package learn.spring.authorization.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="user_info")
public class UserInfo implements BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4025774293295301056L;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="role")
	private String role;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public UserInfo() {
		
	}
	
	public UserInfo(String username,String password,String role){
		this.username = username;
		this.password = password;
		this.role = role.toUpperCase();
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole(){
		return this.role;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new Role(this.role));
		
		return authorities;
	}

	public String getPassword() {
		return this.password;
	}

	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	

	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
