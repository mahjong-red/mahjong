package cn.mahjong.web.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.sys.user.impl.UserImpl;

@Controller
@RequestMapping("login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public BaseObject onLogin(){
		return userService.getObject(UserImpl.class, 1);
	}
	
}
