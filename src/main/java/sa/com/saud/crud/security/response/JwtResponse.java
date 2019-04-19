package sa.com.saud.crud.security.response;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author salghamdi
 *
 */
public class JwtResponse {
	private String token;
	private long timeout;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * @param accessToken
	 * @param username
	 * @param authorities
	 */
	public JwtResponse(String accessToken, long timeout , String username, Collection<? extends GrantedAuthority> authorities) {
		this.token = accessToken;
		this.timeout = timeout;
		this.username = username;
		this.authorities = authorities;
	}

	/**
	 * @return
	 */
	public String getAccessToken() {
		return token;
	}

	/**
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	/**
	 * @return
	 */
	public String getTokenType() {
		return type;
	}

	/**
	 * @param tokenType
	 */
	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
	
	
}