package cn.mahjong.persist.sys.user;

import cn.mahjong.model.sys.user.User;
import cn.mahjong.persist.base.BaseDao;

public interface UserDao extends BaseDao{
	
	public User loadByUsername(String username);
}
