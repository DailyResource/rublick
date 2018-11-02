package online.rubick.applications.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.security.authc.Account;
import online.rubick.applications.security.authority.Permission;
import online.rubick.applications.vo.sys.UserVO;

/**
 *  类权限拦截器，根据定义在类上面的权限标签控制类的权限
 */
public class CheckClassInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Permission per = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Permission.class);
		if (null != per) {
			String value = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Permission.class).permission();
			String[] values = value.split(",");
			HttpSession session = request.getSession();
			UserVO userVO = (UserVO) session.getAttribute(Account.SESSION_NAME);
			if (null == userVO) {
				throw new ApplicationException(1001, "请登录");
				// TODO 返回正确的未登录错误
			} else {
				List<String> permissions = userVO.getPermissions();
				boolean hasPermission = false;
				for (int i = 0; i < values.length; i++) {
					for (String permission : permissions) {
						if (values[i].equals(permission)) {
							hasPermission = true;
							break;
						}
					}
				}
				if (!hasPermission) {
					throw new ApplicationException(1002, "无权限");
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
