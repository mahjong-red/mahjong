package cn.mahjong.persist.sys.user;

import cn.mahjong.model.sys.user.AdminUser;
import cn.mahjong.persist.base.BaseDao;

public interface AdminUserDao extends BaseDao{
	
	public AdminUser loadByUsername(String username);
}
