package cn.mahjong.model.sys.user;

import cn.mahjong.enums.persist.Sex;
import cn.mahjong.enums.persist.UserStatus;
import cn.mahjong.model.base.Bmo;

public interface User extends Bmo{

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
}
