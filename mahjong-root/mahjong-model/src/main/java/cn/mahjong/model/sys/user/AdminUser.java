package cn.mahjong.model.sys.user;

import java.util.Set;

import cn.mahjong.enums.persist.Sex;
import cn.mahjong.enums.persist.UserStatus;
import cn.mahjong.model.base.Bmo;
import cn.mahjong.model.sys.role.Role;

public interface AdminUser extends Bmo{

	public String getUsername();

	public void setUsername(String username);

	public String getNickname();

	public void setNickname(String nickname);

	public String getPassword();

	public void setPassword(String password);

	public Sex getSex();

	public void setSex(Sex sex);

	public UserStatus getUserStatus();

	public void setUserStatus(UserStatus userStatus);
	
	public Set<Role> getRoleSet();

	public void setRoleSet(Set<Role> roleSet);
}
