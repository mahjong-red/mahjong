package cn.mahjong.model.sys.role.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import cn.mahjong.model.base.impl.BmoImpl;
import cn.mahjong.model.sys.role.Role;

@Entity
@Table(name = "sys_role")
@Proxy(lazy = true, proxyClass = Role.class)
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
	@Column(name = "code", length = 64 , unique=true)
	private String code;

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

	
}
