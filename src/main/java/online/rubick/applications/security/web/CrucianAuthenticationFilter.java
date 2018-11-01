package online.rubick.applications.security.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import online.rubick.applications.util.WebUtil;

/**
 * 自定义登录认证过滤器;
 * @author Medeson.Zhang
 * @Date 2016年5月17日
 */
public class CrucianAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	public static final String USERNAME_KEY = "loginName";
	public static final String PASSWORD_KEY = "password";
	public static final String VERIFY_KEY = "verifycode";
	
	//标识用户登录的目标系统:BSS/OSS/EVK/AUGTEK...
	public static final String SYSTEM_KEY	= "system";
	
	private Logger logger = LoggerFactory.getLogger(CrucianAuthenticationFilter.class);

	private String usernameParameter = USERNAME_KEY;
	private String passwordParameter = PASSWORD_KEY;
	private String verifycodeParameter = VERIFY_KEY;
	private String systemParameter = SYSTEM_KEY;
	private SessionRegistryClass sessionRegistry;
	
	private boolean postOnly = true;

	public CrucianAuthenticationFilter(SessionRegistryClass sessionRegistry) {
		super(new AntPathRequestMatcher("/login", "POST"));
		this.sessionRegistry = sessionRegistry;
	}

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = request.getParameter(usernameParameter);
		String password = request.getParameter(passwordParameter);
		String verifycode = request.getParameter(verifycodeParameter);
		String system = request.getParameter(systemParameter);
		List<SessionAuthenticationStrategy> list = new ArrayList<SessionAuthenticationStrategy>();
		//使用security中的sessionRegistry构造两个  registerSessionStrategy登录时将用户存入Principal ，concurrentSessionStrategy是后面判断session个数的 ，这里用一个CompositeSessionAuthenticationStrategy 的list来加载这两个类
		SessionAuthenticationStrategy registerSessionStrategy = new RegisterSessionAuthenticationStrategy(sessionRegistry);
		SessionAuthenticationStrategy concurrentSessionStrategy = new ConcurrentSessionControlAuthenticationStrategy(sessionRegistry);
		list.add(registerSessionStrategy);
		list.add(concurrentSessionStrategy);
		SessionAuthenticationStrategy sessionStrategy = new CompositeSessionAuthenticationStrategy(list);
		super.setSessionAuthenticationStrategy(sessionStrategy);//继承的父类的sessionStrategy 默认是nullRegisterStrategy ,不会去加载authention，需要把它改成SessionAuthenticationStrategy
		if (username == null) {
			username = "";
		}

		if (password == null) {
			password = "";
		}
		
		if (verifycode == null) {
			verifycode = "";
		}

		username = username.trim();

		CrucianAuthenticationToken authRequest = new CrucianAuthenticationToken(username, password,system,verifycode);

		setDetails(request, authRequest);
		
		Authentication auth = null;
		try{
			auth = this.getAuthenticationManager().authenticate(authRequest);
		}
		catch (BadCredentialsException e){
			if(WebUtil.isAjaxReq(request)){
				String jsonObject = "{\"code\":1001,\"message\":\"" + e.getMessage() + ".\"}";
				try {
					WebUtil.ajaxResponse(jsonObject, HttpServletResponse.SC_BAD_REQUEST, response);
				} catch (IOException ex) {
					logger.error("响应Ajax请求出现问题.",ex);
				}
			}
			else{
				throw e;
			}
		}
		return auth;
	}

	protected void setDetails(HttpServletRequest request, CrucianAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	public void setUsernameParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.usernameParameter = usernameParameter;
	}

	public void setPasswordParameter(String passwordParameter) {
		Assert.hasText(passwordParameter, "Password parameter must not be empty or null");
		this.passwordParameter = passwordParameter;
	}

	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getUsernameParameter() {
		return usernameParameter;
	}

	public final String getPasswordParameter() {
		return passwordParameter;
	}

	public String getSystemParameter() {
		return systemParameter;
	}

	public void setSystemParameter(String systemParameter) {
		this.systemParameter = systemParameter;
	}
	
}
