/**   
 * @Title: WebUtil.java 
 * @Package com.mvc.util 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author smw  
 * @date 2017年6月28日 上午10:17:42 
 */
package online.rubick.applications.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * @author smw
 * @ClassName: WebUtil
 * @Description: TODO
 * @author smw
 * 
 */
public class WebUtil {

	/**
	 * 获取完整的访问url
	 * 
	 * @param in
	 * @return
	 */
	public static String getFullUrl(HttpServletRequest request) {
		StringBuffer requstUrl = request.getRequestURL();
		if (request.getQueryString() != null) {
			requstUrl.append('?');
			requstUrl.append(request.getQueryString());
		}
		return requstUrl.toString();
	}

	/***
	 * 服务器是否是本机
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isLocalIp(HttpServletRequest request) {
		String ip = getClientIpAddr(request);
		return ip.equals("127.0.0.1") || ip.equals("localhost") || ip.equals("0:0:0:0:0:0:0:1");
	}

	/***
	 * 获取客户端ip地址(可以穿透代理)
	 * 
	 * @param request
	 * @return
	 */
	public static String getClientIpAddr(HttpServletRequest request) {

		String ip = request.getHeader("X-Forwarded-For");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("x-real-ip");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("Proxy-Client-IP");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("WL-Proxy-Client-IP");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("HTTP_X_FORWARDED");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("HTTP_CLIENT_IP");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("HTTP_FORWARDED_FOR");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("HTTP_FORWARDED");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("HTTP_VIA");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getHeader("REMOTE_ADDR");
		if (StringUtils.isNotEmpty(ip))
			return ip;

		ip = request.getRemoteAddr();
		return ip;
	}

	public static String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * 获取WEB根目录
	 * 
	 * @return
	 */
	public static String getWebRoot(HttpServletRequest request) {
		return request.getSession().getServletContext().getRealPath("/");
	}

	private static final String XMLHTTPREQUEST = "XMLHttpRequest";
	private static final String X_REQUESTED_WITH = "X-Requested-With";

	/**
	 * 判断客户端请求是否为Ajax请求。 一般JQuery的请求都满足这个用法。
	 * 有些其他的客户端框架，在做ajax请求时不一定适用。主要原因是请求头不一定会包含
	 * X-Requested-With:XMLHttpRequest信息。
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxReq(final HttpServletRequest request) {
		if (XMLHTTPREQUEST.equalsIgnoreCase(request.getHeader(X_REQUESTED_WITH))) {
			return true;
		}
		return false;
	}

	/**
	 * 请求响应。
	 * 
	 * @param message
	 *            响应内容
	 * @param responseCode
	 *            响应码
	 * @param response
	 * @throws IOException
	 */
	public static void ajaxResponse(String message, int responseCode, HttpServletResponse response) throws IOException {
		String contentType = "application/json";
		response.setContentType(contentType);
		response.setCharacterEncoding("UTF-8");
		response.setStatus(responseCode);
		PrintWriter out = response.getWriter();
		out.print(message);
		out.flush();
		out.close();
	}
}
