package cn.mahjong.core.sys.user;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.model.sys.user.AdminUser;

public interface AdminUserService extends BaseService{
	
	public AdminUser loadByUserName(String username);
}
