package online.rubick.applications.security.authc;

import java.util.Collection;

/**
 * 
 * 认证通过后的返回信息<br>
 * 
 */
public interface Account {
	
	static final String SESSION_NAME = "SECURITY_LOGIN_USER_ACCOUNT";

    long getUserID();

    void setUserID(long userID);
    
    Collection<String> getRoles();

}
