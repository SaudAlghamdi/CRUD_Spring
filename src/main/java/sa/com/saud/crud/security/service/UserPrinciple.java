package sa.com.saud.crud.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import sa.com.saud.crud.model.CRUDUser;

/**
 * @author salghamdi
 *
 */
public class UserPrinciple implements UserDetails {

	private long id;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * @param id
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public UserPrinciple(long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	/**
	 * @param crudUser
	 * @return
	 */
	public static UserPrinciple build(CRUDUser crudUser) {

		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		authorities.add(new SimpleGrantedAuthority(crudUser.getRole()));

		return new UserPrinciple(crudUser.getId(), crudUser.getUsername(), crudUser.getPassword(), authorities);

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
