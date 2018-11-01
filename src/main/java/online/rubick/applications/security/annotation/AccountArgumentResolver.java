package online.rubick.applications.security.annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import online.rubick.applications.security.authc.Account;

/**
 * Security 账户对象解析，适用于RestController
 * 
 */
@Component
public class AccountArgumentResolver implements HandlerMethodArgumentResolver {
    
    /*
     * 返回false则不会进入到resolveArgument方法，返回true则会
     */
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        SessionAccount annot = parameter.getParameterAnnotation(SessionAccount.class);

        return ((annot != null) && (Account.class.isAssignableFrom(parameter.getParameterType())) && (!StringUtils
                .hasText(annot.value())));
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(Account.SESSION_NAME);
        return account;
    }

}
