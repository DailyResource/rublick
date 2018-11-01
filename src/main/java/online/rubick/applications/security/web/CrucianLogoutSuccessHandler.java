package online.rubick.applications.security.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import online.rubick.applications.util.WebUtil;

@Component
public class CrucianLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
		implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		if (WebUtil.isAjaxReq(request)) {
			response.getWriter().print("{\"code\":200,\"message\":\"SUCCESS\"}");
			response.getWriter().flush();
		} else {
			super.handle(request, response, authentication);
		}
	}

}
