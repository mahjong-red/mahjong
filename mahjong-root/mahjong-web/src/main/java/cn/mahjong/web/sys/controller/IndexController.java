package cn.mahjong.web.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mahjong.core.sys.resource.ResourceService;
import cn.mahjong.model.sys.resource.Resource;

@Controller
@RequestMapping()
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping()
	public String main(HttpServletRequest request, HttpServletResponse response, Model model){
		List<Resource> list = resourceService.findByParent(null);
		model.addAttribute("resources", list);
		return "main";
	}

}
