package online.rubick.applications.security.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import online.rubick.applications.util.WebUtil;

@Controller
@Api(description = "Session丢失的转向接口")
@RestController
public class SessionController {

	private Logger log = LoggerFactory.getLogger(SessionController.class);
	
	@ApiOperation(value = "Session失效", notes = "Session失效处理")
	@RequestMapping(method = RequestMethod.GET, value = "/invalidSession")
	public void invalidateSession(HttpServletRequest request, HttpServletResponse response) {
		if (WebUtil.isAjaxReq(request)) {
			String jsonObject = "{\"code\":1001,\"message\":\"登录已过期.\"}";
			try {
				WebUtil.ajaxResponse(jsonObject, HttpServletResponse.SC_BAD_REQUEST, response);
			} catch (IOException ex) {
				log.error("响应Ajax请求出现问题.", ex);
			}
		} else {
			try {
				response.sendRedirect("/index.html");
			} catch (IOException e) {
				log.error("跳转到登录页出错：", e);
			}
		}

	}

}
