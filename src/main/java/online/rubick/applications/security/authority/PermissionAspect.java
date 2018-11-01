package online.rubick.applications.security.authority;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import online.rubick.applications.exception.ApplicationException;
import online.rubick.applications.security.authc.Account;
import online.rubick.applications.vo.sys.UserVO;

/**
 * 自定义权限控制 目前是根据在Controller上增加注解，通过permission传过来所需要的权限。 适配器：
 * 如果是在每个controller的方法上定义所需要的permission根据传过来的permission 跟userVO中 role所包含的权限比较
 * 如果是在数据库中定义好url 和所需要的权限， 在这里根据request中的url（antMatch） 去查询所需要的权限，再跟userVO中
 * role所包含的权限比较 如果是在配置文件中配置好的url 和权限对应，也在这里读取即可
 *
 * 用法，如果有多个权限用,分割 @Permission(permission="project_add,project_delete")
 * 
 * @author 林鹏
 */
@Component
@Aspect
public class PermissionAspect {

    @Pointcut("@annotation(online.rubick.applications.security.authority.Permission)")
    private void cut() {
    }

    @Before("cut()")
    public void advice(final JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String value = getServiceMethodPermission(joinPoint);
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

    private String getServiceMethodPermission(JoinPoint joinPoint) throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        @SuppressWarnings("rawtypes")
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String permission = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                @SuppressWarnings("rawtypes")
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    permission = method.getAnnotation(Permission.class).permission();
                    break;
                }
            }
        }
        return permission;
    }
}
