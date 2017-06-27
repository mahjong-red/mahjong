package cn.mahjong.core.sys.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.mahjong.core.base.impl.BaseServiceImpl;
import cn.mahjong.core.sys.user.AdminUserService;
import cn.mahjong.model.sys.user.AdminUser;
import cn.mahjong.persist.sys.user.AdminUserDao;

@Service("adminUserService")
public class AdminUserServiceImpl extends BaseServiceImpl implements AdminUserService ,UserDetailsService{

	@Autowired
	private AdminUserDao adminUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminUser user = loadByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(messages.getMessage("UsernameNotFound", "用户不存在"));
		}
		return (UserDetails) user;
	}

	@Cacheable(value = {"abc"})
	@Override
	public AdminUser loadByUserName(String username) {
		return adminUserDao.loadByUsername(username);
	}
}
