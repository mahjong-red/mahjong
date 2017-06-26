package cn.mahjong.core.sys.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.mahjong.core.base.impl.BaseServiceImpl;
import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.persist.sys.user.UserDao;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService ,UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = loadByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException(messages.getMessage("UsernameNotFound", "用户不存在"));
		}
		return (UserDetails) user;
	}

	@Override
	public User loadByUserName(String username) {
		return userDao.loadByUsername(username);
	}
}
