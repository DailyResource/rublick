package online.rubick.applications.security.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import online.rubick.applications.util.WebUtil;

@Component
public class CrucianAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws IOException, ServletException {

		if (WebUtil.isAjaxReq(request)) {
			String sessionId = request.getSession().getId();
			response.getWriter().print("{\"code\":200,\"message\":\"SUCCESS\",\"sessionId\":\""+sessionId+"\"}");
			response.getWriter().flush();
		} else {
			super.onAuthenticationSuccess(request, response, auth);
		}
	}

}
