package cn.mahjong.web.sys.role;

import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.role.Role;

public class RoleDto {
	
	private Role target;

	public RoleDto(Role target) {
		super();
		this.target = target;
	}
	
	public long getId(){
		return target.getId();
	}
	
	public String getName(){
		return target.getName();
	}
	
	public String getCode(){
		return target.getCode();
	}
	
	public String getResourceSet(){
		StringBuilder sb = new StringBuilder();
		for (Resource item : target.getResourceSet()) {
			sb.append(item.getId());
			sb.append(",");
		}
		return sb.toString();
	}
}
