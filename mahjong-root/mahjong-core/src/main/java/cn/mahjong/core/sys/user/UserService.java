package cn.mahjong.core.sys.user;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.model.sys.user.User;

public interface UserService extends BaseService{
	
	public User loadByUserName(String username);
}
