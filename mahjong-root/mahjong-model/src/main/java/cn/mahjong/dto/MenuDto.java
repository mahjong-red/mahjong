package cn.mahjong.dto;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.role.Role;
import cn.mahjong.model.sys.user.AdminUser;

public class MenuDto {
	
	private long id;
	
	private String name;
	
	private String url;
	
	private String icon;
	
	public MenuDto(Resource resource) {
		this.id = resource.getId();
		this.name = resource.getName();
		this.url = resource.getUrl();
		this.icon = resource.getIconCls();
		if (!CollectionUtils.isEmpty(resource.getRoleSet())) {
			for (Role item : resource.getRoleSet()) {
				roles.add(item.getCode());
			}
		}
	}

	private Set<String> roles = new HashSet<String>();
	
	private List<MenuDto> children = new ArrayList<MenuDto>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public Set<String> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public Set<String> getDescendantAuthorityMark() {
		return this.getDescendantAuthorityMark(this);
	}
	
	private Set<String> getDescendantAuthorityMark(MenuDto menu) {
		Set<String> result = new HashSet<String>();
		result.addAll(menu.roles);
		if (!menu.getChildren().isEmpty()) {
			for (MenuDto menu1 : menu.getChildren()) {
				result.addAll(this.getDescendantAuthorityMark(menu1));
			}
		}
		return result;
	}
	
	public boolean hasAuthorited(AdminUser currentUser) {
		List<String> roles = new ArrayList<String>(currentUser.getRoleSet().size());
		for (Role role : currentUser.getRoleSet()) {
			roles.add(role.getCode());
		}
		for (String authorityMark : this.getDescendantAuthorityMark()) {
			if (roles.contains(authorityMark)) {
				return true;
			}
		}
		return false;
	}


}
