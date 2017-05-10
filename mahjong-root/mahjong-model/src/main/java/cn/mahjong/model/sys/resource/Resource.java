package cn.mahjong.model.sys.resource;

import java.util.Set;

import cn.mahjong.enums.persist.ResourceType;
import cn.mahjong.model.base.Bmo;
import cn.mahjong.model.sys.role.Role;

public interface Resource extends Bmo{

	public String getName();

	public void setName(String name);

	public String getUrl();

	public void setUrl(String url);

	public String getSequence();

	public void setSequence(String sequence);

	public ResourceType getResourceType();

	public void setResourceType(ResourceType resourceType);
	
	public Set<Role> getRoleSet();

	public void setRoleSet(Set<Role> roleSet);
	
	public Resource getParent();

	public void setParent(Resource parent);
	
}
