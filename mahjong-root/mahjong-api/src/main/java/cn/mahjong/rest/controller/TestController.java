package cn.mahjong.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.dto.RestResp;
import cn.mahjong.enums.persist.Sex;
import cn.mahjong.enums.persist.UserStatus;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.model.sys.user.impl.UserImpl;
import cn.mahjong.utils.SpringContextUtil;

@Controller
@RequestMapping(value = "rest/test")
public class TestController {
	
	@Autowired
	public UserService userService;
	
	@RequestMapping("1")
	@ResponseBody
	public String getString(String name){
		try {
			User user = new UserImpl();
			user.setNickname("红中");
			user.setUsername(name);
			user.setPassword("pwd");
			user.setSex(Sex.MAN);
			user.setUserStatus(UserStatus.ENABLE);
			userService.save1(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@RequestMapping("getbyid")
	@ResponseBody
	public RestResp get(int id){
		RestResp resp = new RestResp("0", "", null);
		User news = (UserImpl) userService.getObject(UserImpl.class, 1);
		resp.setData(news);
		return resp;
	}
}
