package cn.mahjong.core.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.model.sys.user.impl.UserImpl;
import cn.mahjong.utils.SpringContextUtil;

public class SecurityHelp {
	
	public static User getCurrentUser() {
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
		User currentUser = (User) securityContext.getAuthentication().getPrincipal();

		UserService userService = (UserService) SpringContextUtil.getBean("userService");

		return (User) userService.getObject(UserImpl.class, currentUser.getId());
	}
	
	public static String getCurrentUserName() {
		return getCurrentUser() == null ? "" : getCurrentUser().getUsername();
	}

	public static void resetAuthentication(User user) {
		Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword());
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		SecurityContext securityContext = (SecurityContext) request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		securityContext.setAuthentication(authentication);
	}

}
