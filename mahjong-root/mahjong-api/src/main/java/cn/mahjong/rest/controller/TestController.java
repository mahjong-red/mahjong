package cn.mahjong.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.dto.RestResp;

@Controller
@RequestMapping(value = "rest/test")
public class TestController {
	
	@Autowired
	public UserService userService;
	
	@RequestMapping("getbyid")
	@ResponseBody
	public RestResp get(int id){
		RestResp resp = new RestResp("0", "", null);
		return resp;
	}
}
