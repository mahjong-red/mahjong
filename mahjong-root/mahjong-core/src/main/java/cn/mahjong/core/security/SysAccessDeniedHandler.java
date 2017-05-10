package cn.mahjong.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import cn.mahjong.dto.RestResp;

import com.alibaba.fastjson.JSONObject;

/**
 * 访问被拒绝
 */
public class SysAccessDeniedHandler implements AccessDeniedHandler {
	
	private static final String RESPONSE_TYPE_JSON = "application/json";
	
	// 错误页面地址
	private String errorPage;

	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		
		String header = request.getHeader("accept");
		response.setStatus(HttpStatus.SC_FORBIDDEN);
		if (StringUtils.contains(header, RESPONSE_TYPE_JSON)){
			RestResp resp = new RestResp("999", "Access Denied", null);
			response.getWriter().print(JSONObject.toJSONString(resp));
			return;
		}
		response.sendRedirect(request.getContextPath() + errorPage);
        return;
	}

	/**
	 * @return the errorPage
	 */
	public String getErrorPage() {
		return errorPage;
	}

	/**
	 * @param errorPage the errorPage to set
	 */
	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
}
