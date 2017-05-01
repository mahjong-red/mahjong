package cn.mahjong.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.model.App;

@Controller
@RequestMapping("test")
public class TestController {

	@RequestMapping("")
	@ResponseBody
	public String test(){
		return App.getString();
	}
	
	
}
