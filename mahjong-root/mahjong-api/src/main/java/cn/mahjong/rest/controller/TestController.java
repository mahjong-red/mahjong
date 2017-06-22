package cn.mahjong.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.dto.RestResp;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.model.sys.user.impl.UserImpl;

@Controller
@RequestMapping(value = "rest/test")
public class TestController {
	
	@Autowired
	public UserService userService;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@RequestMapping("getbyid")
	@ResponseBody
	public RestResp get(int id){
		RestResp resp = new RestResp("0", "", null);
		User user = (UserImpl) userService.getObject(UserImpl.class, 1);
		redisTemplate.opsForValue().set("user","hello world");
		resp.setData(user);
		return resp;
	}
}
