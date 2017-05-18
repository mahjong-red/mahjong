package cn.mahjong.core.security;

import java.util.Collection;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class MahjongAccessDecisionManager implements AccessDecisionManager {

	protected static final Logger logger = Logger.getLogger(MahjongAccessDecisionManager.class);

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("AniuAccessDecisionManager.decide(Authentication, Object, Collection<ConfigAttribute>) - configAttributes is null");
			}
			return;
		}

		for (ConfigAttribute ca : configAttributes) {

			String needRole = ((SecurityConfig) ca).getAttribute();

			for (GrantedAuthority ga : authentication.getAuthorities()) {

				if (StringUtils.equals(needRole, ga.getAuthority())) {
					// 权限验证通过
					return;
				}
			}
		}
		// 权限验证失败
		throw new AccessDeniedException("access denied");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
