package cn.mahjong.web.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mahjong.core.sys.user.UserService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("")
	public String test1(){
		System.out.println("main");
		return "main";
	}
	
	
}
