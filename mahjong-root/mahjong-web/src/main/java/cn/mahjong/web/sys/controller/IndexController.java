package cn.mahjong.web.sys.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.mahjong.core.security.SecurityHelp;
import cn.mahjong.core.sys.resource.ResourceService;
import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.role.Role;
import cn.mahjong.model.sys.user.User;

@Controller
@RequestMapping()
public class IndexController {

	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping()
	public String main(HttpServletRequest request, HttpServletResponse response, Model model){
		List<Resource> list = resourceService.findByParent(21L);
		filterByUser(list, SecurityHelp.getCurrentUser());
		model.addAttribute("resources", list);
		return "main";
	}
	
	private void filterByUser(List<Resource> list,User user){
		Iterator<Resource> it = list.iterator();
		while (it.hasNext()) {
			Resource item = it.next();
			boolean b = true;
			for (Role role : item.getRoleSet()) {
				if (user.getRoleSet().contains(role)) {
					if (!CollectionUtils.isEmpty(item.getChildren())) {
						filterByUser(item.getChildren(), user);
					}
					b = false;
					break;
				}
			}
			if (b) {
				it.remove();
				
			}
		}
	}

}
