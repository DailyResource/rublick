package online.rubick.applications.security.web;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import online.rubick.applications.entity.sys.SysUser;
import online.rubick.applications.security.authc.Account;
import online.rubick.applications.service.SysUserService;
import online.rubick.applications.vo.sys.UserVO;

@WebListener
@Component
public class CrucianSessionListener implements HttpSessionListener {
	
	@Autowired
	private SysUserService userSvc;
    
    @Autowired
    HttpServletRequest request;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		//用户登录，创建Session时，需要实现的内容；
	}

	//通过这两个方法，可以实现在线用户统计;
	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		//用户退出登录，session失效时，需要实现的业务逻辑；
		//退出登录 
		SysUser user = new SysUser();
		UserVO userVO = (UserVO) request.getSession().getAttribute(Account.SESSION_NAME);
		if(null!=userVO){
			BeanUtils.copyProperties(userVO, user);
			user.setName(userVO.getUserName());
			userSvc.logout(user);
		}
	}

}
