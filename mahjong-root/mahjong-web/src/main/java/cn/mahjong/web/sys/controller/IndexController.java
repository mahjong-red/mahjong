package cn.mahjong.web.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.sys.user.impl.UserImpl;

@Controller
@RequestMapping("")
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	@ResponseBody
	public BaseObject test(){
		return userService.getObject(UserImpl.class, 1);
	}
	
	@RequestMapping("1")
	public String test1(){
		return "login";
	}
	
	
}
