package cn.mahjong.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import cn.mahjong.dto.RestResp;

/**
 * 访问被拒绝
 */
@Component
public class MahjongAccessDeniedHandler implements AccessDeniedHandler {
	
	private static final String RESPONSE_TYPE_JSON = "application/json";
	
	@Autowired
	public ResourceBundleMessageSource messageSource;
	
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		
		String header = request.getHeader("accept");
		response.setStatus(HttpStatus.SC_FORBIDDEN);
		if (StringUtils.contains(header, RESPONSE_TYPE_JSON)){
			RestResp resp = new RestResp("403", messageSource.getMessage("AbstractAccessDecisionManager.accessDenied", null, "", LocaleContextHolder.getLocale()) , null);
			response.getWriter().print(JSONObject.toJSONString(resp));
			return;
		}
		response.sendRedirect(request.getContextPath() + "/views/error/403.jsp");
        return;
	}

}
