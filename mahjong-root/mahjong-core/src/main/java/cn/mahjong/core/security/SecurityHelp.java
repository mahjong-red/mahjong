package cn.mahjong.core.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.mahjong.core.sys.user.AdminUserService;
import cn.mahjong.model.sys.user.AdminUser;
import cn.mahjong.model.sys.user.impl.AdminUserImpl;
import cn.mahjong.utils.SpringContextUtil;

public class SecurityHelp {
	
	public static AdminUser getCurrentUser() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (securityContext == null) {
			return null;
		}
		if (securityContext.getAuthentication() == null) {
			return null;
		}
		
		if (!(securityContext.getAuthentication().getPrincipal() instanceof UserDetails)) {
			return null;
		}
		AdminUser currentUser = (AdminUser) securityContext.getAuthentication().getPrincipal();

		AdminUserService userService = (AdminUserService) SpringContextUtil.getBean("adminUserService");

		return (AdminUser) userService.getObject(AdminUserImpl.class, currentUser.getId());
	}
	
	public static String getCurrentUserName() {
		return getCurrentUser() == null ? "" : getCurrentUser().getUsername();
	}

	public static void resetAuthentication(AdminUser user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		securityContext.setAuthentication(authentication);
	}

}
