package cn.mahjong.core.sys.user.impl;

import org.springframework.stereotype.Service;

import cn.mahjong.core.base.impl.BaseServiceImpl;
import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.model.sys.user.User;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl implements UserService {

	@Override
	public void save1(User user) throws Exception {
		save(user);
		
	}
}
