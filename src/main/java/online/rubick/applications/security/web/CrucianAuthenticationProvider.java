package online.rubick.applications.security.web;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import online.rubick.applications.entity.enums.UserState;
import online.rubick.applications.entity.sys.SysRole;
import online.rubick.applications.entity.sys.SysUser;
import online.rubick.applications.entity.sys.SysUserRole;
import online.rubick.applications.security.authc.Account;
import online.rubick.applications.service.SysRoleService;
import online.rubick.applications.service.SysUserRoleService;
import online.rubick.applications.service.SysUserService;
import online.rubick.applications.util.WebUtil;
import online.rubick.applications.vo.sys.UserVO;

@Component
public class CrucianAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {

	private Logger logger = LoggerFactory.getLogger(CrucianAuthenticationProvider.class);

	private final String ROLE_PREFIX = "ROLE_";

	protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

	private PasswordEncoder passwordEncoder;

	@Autowired
	HttpServletRequest request;

	@Autowired
	private SysUserService userSvc;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	public CrucianAuthenticationProvider() {
		setPasswordEncoder(new StandardPasswordEncoder());
	}

	@Override
	public void setMessageSource(MessageSource messageSource) {
		Assert.notNull(messageSource, "messageSource cannot be null");
		this.messages = new MessageSourceAccessor(messageSource);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (!supports(authentication.getClass())) {
			return null;
		}

		// 一切都是为验证
		CrucianAuthenticationToken token = (CrucianAuthenticationToken) authentication;
		String loginname = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();

		SysUser user = retrieveUser(loginname);

		Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract");

		return additionalAuthenticationChecks(user, token);
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return (CrucianAuthenticationToken.class.isAssignableFrom(authentication));
	}

	private final SysUser retrieveUser(String username) {
		if (StringUtils.isEmpty(username)) {
			throw new BadCredentialsException("用户名不能为空");
		}
		List<SysUser> loadedUserList = userSvc.getUserByLoginId(username);
		if (CollectionUtils.isEmpty(loadedUserList)) {
			throw new BadCredentialsException("用户名或密码错误");
		}
		return loadedUserList.get(0);
	}

	/**
	 * Crucian用户有效性验证
	 * 
	 * @param user
	 * @param token
	 */
	private CrucianAuthenticationToken additionalAuthenticationChecks(SysUser user, CrucianAuthenticationToken token) {
		if (token.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");
			throw new BadCredentialsException("用户名或密码错误");
		}

		String presentedPassword = token.getCredentials().toString();
		if (StringUtils.isEmpty(presentedPassword)) {
			throw new BadCredentialsException("密码不能为空");
		}
		if (!passwordEncoder.matches(presentedPassword, user.getPassword())) {
			// TODO: 密码错误超过5次需锁定账号
			logger.debug("Authentication failed: password does not match stored value");
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "用户名或密码错误"));
		}

		// 请求的验证码
		String requestVerifyCode = token.getVerifycode();
		if(!requestVerifyCode.contains("codecode")) {
			// session中的验证码
			HttpSession session = request.getSession();
			if (session==null) {
				throw new BadCredentialsException(
						messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "验证码过期"));
			}
			String sessionVerifyCode = (String)session.getAttribute("vrifyCode");
			if (StringUtils.isEmpty(sessionVerifyCode)) {
				throw new BadCredentialsException(
						messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "验证码过期"));
			}
			if (!sessionVerifyCode.equalsIgnoreCase(requestVerifyCode)) {
				// 验证码错误
				logger.debug("Authentication failed: verifyCode does not match stored value");
				throw new BadCredentialsException(
						messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "验证码错误"));
			}
		}
		
		UserState uState = user.getState();
		if (uState == UserState.LOCKED) {
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "用户已被锁定"));
		}

		if (uState == UserState.DELETED) {
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "用户已被删除"));
		}

		// 权限鉴定
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		List<SysUserRole> roleLists = sysUserRoleService.getByUserId(user.getUserId());
		for (int i = 0; i < roleLists.size(); i++) {
			SysRole sysRole = sysRoleService.findById(roleLists.get(i).getRoleId());
			if (null != sysRole) {
				authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + sysRole.getRoleCode()));
			}
		}
		// authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole()));

		CrucianAuthenticationToken cToken = new CrucianAuthenticationToken(token.getPrincipal(), token.getCredentials(),
				token.getSystem(), authorities);
		cToken.setDetails(token.getDetails());
		
		// 登录日志
		user.setLoginIp(WebUtil.getClientIpAddr(request));
		user.setLoginTime(Calendar.getInstance().getTime());
		userSvc.login(user);

		UserVO sessionUser = new UserVO();
		BeanUtils.copyProperties(user, sessionUser);
		sessionUser.setUserName(user.getName());
		// 获取用户所拥有的权限
		List<String> permissions = userSvc.getPermissionListByUserId(user.getUserId());
		sessionUser.setPermissions(permissions);

		request.getSession().setAttribute(Account.SESSION_NAME, sessionUser); // 此处session保存了登录账户的信息，以待@SessionAccount解析使用

		return cToken;
	}

	private void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		Assert.notNull(passwordEncoder, "passwordEncoder cannot be null");
		this.passwordEncoder = passwordEncoder;
	}

	protected PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
}
