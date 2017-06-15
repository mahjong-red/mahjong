package cn.mahjong.model.sys.role;

import java.util.Set;

import cn.mahjong.model.base.Bmo;
import cn.mahjong.model.sys.resource.Resource;

public interface Role extends Bmo {

	public String getName();

	public void setName(String name);

	public String getCode();

	public void setCode(String code);
	
	public Set<Resource> getResourceSet();

	public void setResourceSet(Set<Resource> resourceSet);
}
