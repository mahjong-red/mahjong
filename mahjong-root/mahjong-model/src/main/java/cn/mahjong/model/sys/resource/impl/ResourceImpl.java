package cn.mahjong.model.sys.resource.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import cn.mahjong.enums.persist.ResourceType;
import cn.mahjong.model.base.impl.BmoImpl;
import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.role.Role;
import cn.mahjong.model.sys.role.impl.RoleImpl;

@Entity
@Table(name = "sys_resource")
@Proxy(lazy = true, proxyClass = Resource.class)
@Where(clause = " is_delete=0 ")
@SQLDelete(sql=" UPDATE sys_resource SET is_delete = 1 WHERE id = ? " ,check=ResultCheckStyle.COUNT)
public class ResourceImpl extends BmoImpl implements Resource {

	private static final long serialVersionUID = -3752135427372331139L;

	/**
	 * 名称
	 */
	@Column(name = "name", length = 64)
	private String name;

	/**
	 * 访问路径
	 */
	@Column(name = "url", length = 64)
	private String url;

	/**
	 * 排序
	 */
	@Column(name = "sequence",nullable=true)
	private int sequence = 1;
	
	/**
	 * 显示图标
	 */
	@Column(name = "icon_cls", length = 64)
	private String iconCls;

	/**
	 * 类型
	 */
	@Column(name = "resource_type", nullable = false, length = 1)
	@Type(type = "cn.mahjong.enums.persist.PersistEnumType", parameters = {@Parameter(name = "enumClass", value = "cn.mahjong.enums.persist.ResourceType")})
	private ResourceType resourceType;

	/**
	 * 角色
	 */
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = RoleImpl.class)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	@JoinTable(name = "sys_resource_role", 
		joinColumns = {@JoinColumn(referencedColumnName = "id", name = "resource_id", nullable = false)}, 
		inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "role_id",  nullable = false)})
	@Where(clause="is_delete=0")
	private Set<Role> roleSet = new HashSet<Role>();

	/**
	 * 上级资源
	 */
	@ManyToOne(targetEntity = ResourceImpl.class, fetch = FetchType.LAZY)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	@JoinColumn(name = "parent_id")
	private Resource parent;

	/**
	 * 下级资源
	 */
	@OneToMany(targetEntity = ResourceImpl.class, mappedBy = "parent", fetch = FetchType.LAZY)
	@Cascade(value = {CascadeType.SAVE_UPDATE, CascadeType.DELETE})
	@OrderBy("sequence asc")
	@Where(clause="is_delete=0")
	private List<Resource> children = new ArrayList<Resource>();

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

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
