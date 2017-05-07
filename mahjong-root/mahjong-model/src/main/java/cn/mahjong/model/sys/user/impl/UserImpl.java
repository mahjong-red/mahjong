package cn.mahjong.model.sys.user.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;

import cn.mahjong.enums.persist.Sex;
import cn.mahjong.enums.persist.UserStatus;
import cn.mahjong.model.base.impl.BmoImpl;
import cn.mahjong.model.sys.user.User;

@Entity
@Table(name = "sys_user")
@Proxy(lazy = true, proxyClass = User.class)
public class UserImpl extends BmoImpl implements User {

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
}
