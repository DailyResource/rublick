package online.rubick.applications.security.web;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

/**
 * 参考UsernamePasswordAuthenticationToken
 * @author Medeson.Zhang
 * @Date 2016年5月17日
 */
public class CrucianAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final Object principal;
	private Object credentials;
	private String verifycode;
	private String system;

	public CrucianAuthenticationToken(Object principal, Object credentials,String system,String verifycode) {
		super(null);
		this.principal = principal;
		this.credentials = credentials;
		this.system = system;
		this.verifycode = verifycode;
		setAuthenticated(false);
	}

	public CrucianAuthenticationToken(Object principal, Object credentials,String system,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		this.system = system;
		super.setAuthenticated(true);
	}

	public Object getCredentials() {
		return this.credentials;
	}

	public Object getPrincipal() {
		return this.principal;
	}

	public String getSystem(){
		return this.system;
	}
	
	public String getVerifycode(){
		return this.verifycode;
	}
	
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		credentials = null;
	}

}
