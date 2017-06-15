package cn.mahjong.model.sys.role.impl;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import cn.mahjong.model.base.impl.BmoImpl;
import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.resource.impl.ResourceImpl;
import cn.mahjong.model.sys.role.Role;

@Entity
@Table(name = "sys_role")
@Proxy(lazy = true, proxyClass = Role.class)
@Where(clause = " is_delete=0 ")
@SQLDelete(sql=" UPDATE sys_role SET is_delete = 1 WHERE id = ? " ,check=ResultCheckStyle.COUNT)
public class RoleImpl extends BmoImpl implements Role {

	private static final long serialVersionUID = -3752138427372381139L;

	/**
	 * 名称
	 */ 
	@Column(name = "name", length = 64)
	private String name;

	/**
	 * 排序
	 */
	@Column(name = "code", length = 64, unique = true)
	private String code;

	/**
	 * 资源
	 */
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = ResourceImpl.class)
	@Cascade(value = {CascadeType.SAVE_UPDATE})
	@JoinTable(name = "sys_resource_role", 
		inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "resource_id", nullable = false)}, 
		joinColumns = {@JoinColumn(referencedColumnName = "id", name = "role_id", nullable = false)})
	@Where(clause="is_delete=0")
	private Set<Resource> resourceSet = new HashSet<Resource>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Set<Resource> getResourceSet() {
		return resourceSet;
	}

	public void setResourceSet(Set<Resource> resourceSet) {
		this.resourceSet = resourceSet;
	}

}
