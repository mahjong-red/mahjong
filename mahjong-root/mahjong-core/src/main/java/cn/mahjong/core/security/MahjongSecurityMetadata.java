package cn.mahjong.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import cn.mahjong.core.sys.resource.ResourceService;
import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.role.Role;

public class MahjongSecurityMetadata implements FilterInvocationSecurityMetadataSource {

	/** logger */
	protected static final Logger logger = Logger.getLogger(MahjongSecurityMetadata.class);

	/** 所有资源信息 */
	private static final Map<String, Collection<ConfigAttribute>> RESOURCES = new HashMap<String, Collection<ConfigAttribute>>();

	@Autowired
	private ResourceService resourceService;

	// 资源匹配器
	//private AntPathMatcher urlMatcher = new AntPathMatcher();

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (RESOURCES.isEmpty()) {
			return null;
		}
		// 获取请求的url地址
		String url = ((FilterInvocation) object).getRequestUrl();
		if (url.indexOf("/?") != -1) {
			url = StringUtils.substringBefore(url, "/?");
		}
		
		if (url.indexOf("?") != -1) {
			url = StringUtils.substringBefore(url, "?");
		}
		Collection<ConfigAttribute> result = RESOURCES.get(url);
		if (result == null) {
			result = new ArrayList<ConfigAttribute>();
			result.add(new SecurityConfig("ROLE_NO_USER"));
		}
		return result;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		if (RESOURCES.isEmpty()) {
			return null;
		}

		Collection<ConfigAttribute> result = new ArrayList<ConfigAttribute>();

		for (Collection<ConfigAttribute> ca : RESOURCES.values()) {
			result.addAll(ca);
		}

		return result;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	/**
	 * 加载权限信息
	 */
	public void initSecurityMeta() {
		// 加载所有资源信息
		List<Resource> resources = (List<Resource>) this.resourceService.loadAllResourceWithRoleTx();

		if (resources == null || resources.isEmpty()) {
			return;
		}
		RESOURCES.clear();
		for (Resource r : resources) {
			if (StringUtils.isBlank(r.getUrl())) {
				continue;
			}
			RESOURCES.put(r.getUrl(), this.generateConfigAttribute(r));
		}
	}

	private Set<ConfigAttribute> generateConfigAttribute(Resource resource) {
		Set<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
		for (Role role : resource.getRoleSet()) {
			configAttributes.add(new SecurityConfig(role.getCode()));
		}
		return configAttributes;
	}
	
}
