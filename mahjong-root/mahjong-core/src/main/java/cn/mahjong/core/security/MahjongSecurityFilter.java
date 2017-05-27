package cn.mahjong.core.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

public class MahjongSecurityFilter extends AbstractSecurityInterceptor implements Filter {

	/** 没有权限错误信息 */
	public static final String ACCESS_DENIED = "Access Denied";
	
	// 权限资源管理对象
	private FilterInvocationSecurityMetadataSource metadata;
	
	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain c) throws IOException, ServletException {
		
		FilterInvocation fi = new FilterInvocation(req, res, c);
        invoke(fi);
	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		
		InterceptorStatusToken token = null;
		
		try {
			// 执行权限校验
			token = super.beforeInvocation(fi);
			
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
			throw new AccessDeniedException(ACCESS_DENIED); 
		} finally {
            super.afterInvocation(token, null);  
        }  
    } 

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig f) throws ServletException {
	}

	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(FilterInvocationSecurityMetadataSource metadata) {
		this.metadata = metadata;
	}

	/**
	 * @see org.springframework.security.access.intercept.AbstractSecurityInterceptor#getSecureObjectClass()
	 */
	@Override
	public Class<?> getSecureObjectClass() {
		return FilterInvocation.class;
	}

	/**
	 * @see org.springframework.security.access.intercept.AbstractSecurityInterceptor#obtainSecurityMetadataSource()
	 */
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.metadata;
	}

}
