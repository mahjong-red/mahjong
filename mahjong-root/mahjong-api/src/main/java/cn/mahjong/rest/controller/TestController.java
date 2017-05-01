package cn.mahjong.rest.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.news.NewsService;
import cn.mahjong.enums.persist.Sex;
import cn.mahjong.model.News;
import cn.mahjong.model.impl.NewsImpl;

@Controller
@RequestMapping(value = "rest/test")
public class TestController {
	
	@Autowired
	@Qualifier("newsService")
	public NewsService newsService;
	
	@RequestMapping("1")
	@ResponseBody
	public String getString(){
		try {
			newsService.save1(new NewsImpl(null,"姜满", "aa", "content", Sex.MAN, new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}
	
	@RequestMapping("getbyid")
	@ResponseBody
	public String get(int id){
		News news = (News) newsService.getObject(NewsImpl.class, 9);
		return news.toString();
	}
}
