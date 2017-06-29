package cn.mahjong.core.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

import cn.mahjong.dto.RestResp;

import com.alibaba.fastjson.JSONObject;

public class MahjongInvalidSessionHandler implements InvalidSessionStrategy {

protected final Log logger = LogFactory.getLog(getClass());
	
	/** 响应类型是JSON */
	private static final String RESPONSE_TYPE_JSON = "application/json";
	
	// 跳转策略
	protected final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	// 跳转地址
	private String redirectUrl;
	
	// 需要移除的cookie
	//private String[] removeCookies;
    
	/**
	 * @see org.springframework.security.web.session.InvalidSessionStrategy#onInvalidSessionDetected(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void onInvalidSessionDetected(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		logger.debug("[AniuInvalidSessionHandler] session invalid");
		
		// 获得请求头信息
		String header = request.getHeader("accept");
		
		// 删除cookie
//        for (String c : removeCookies) {
//        	
//        	Cookie cookie = new Cookie(c, null);
//            cookie.setMaxAge(0);
//            response.addCookie(cookie);
//		}
		
		// 重新创建session
        request.getSession();
        
        if (StringUtils.contains(header, RESPONSE_TYPE_JSON)){
			// AJAX请求时，发现session失效，给于失效的响应信息
			RestResp obj = new RestResp("101", "登录状态已失效，请重新登录！", null);
			
			response.getWriter().print(JSONObject.toJSONString(obj));
			return;
		}
        
        // 跳转页面
        redirectStrategy.sendRedirect(request, response, redirectUrl);
	}

	/**
	 * @return the redirectUrl
	 */
	public String getRedirectUrl() {
		return redirectUrl;
	}

	/**
	 * @param redirectUrl the redirectUrl to set
	 */
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

}
