package cn.mahjong.model.sys.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.ResultCheckStyle;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cn.mahjong.enums.persist.Sex;
import cn.mahjong.enums.persist.UserStatus;
import cn.mahjong.model.base.impl.BmoImpl;
import cn.mahjong.model.sys.role.Role;
import cn.mahjong.model.sys.role.impl.RoleImpl;
import cn.mahjong.model.sys.user.User;

@Entity
@Table(name = "sys_user")
@Proxy(lazy = true, proxyClass = User.class)
@Where(clause = " is_delete=0 ")
@SQLDelete(sql=" UPDATE sys_user SET is_delete = 1 WHERE id = ? " ,check=ResultCheckStyle.COUNT)
public class UserImpl extends BmoImpl implements User,UserDetails {

	private static final long serialVersionUID = -2797367290465806561L;

	/**
	 * 用户名
	 */
	@Column(name = "username", length = 64, unique = true)
	private String username;

	/**
	 * 昵称
	 */
	@Column(name = "nickname", length = 64)
	private String nickname;

	/**
	 * 密码
	 */
	@Column(name = "password", length = 64)
	private String password;

	/**
	 * 性别
	 */
	@Column(name = "sex", nullable = false, length = 1)
	@Type(type = "cn.mahjong.enums.persist.PersistEnumType", parameters = {@Parameter(name = "enumClass", value = "cn.mahjong.enums.persist.Sex")})
	private Sex sex;

	/**
	 * 状态
	 */
	@Column(name = "user_status", nullable = false, length = 1)
	@Type(type = "cn.mahjong.enums.persist.PersistEnumType", parameters = {@Parameter(name = "enumClass", value = "cn.mahjong.enums.persist.UserStatus")})
	private UserStatus userStatus;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity = RoleImpl.class)
	@Cascade(value = { CascadeType.SAVE_UPDATE})
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false) }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false) })
	@Where(clause="is_delete=0")
	private Set<Role> roleSet = new HashSet<Role>();
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public Set<Role> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<Role> roleSet) {
		this.roleSet = roleSet;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		for (Role role : roleSet) {
			list.add(new SimpleGrantedAuthority(role.getCode()));
		}
		return list;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
