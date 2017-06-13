package cn.mahjong.web.sys.resource.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.sys.resource.ResourceService;
import cn.mahjong.dto.TreeDto;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.resource.impl.ResourceImpl;
import cn.mahjong.web.base.controller.BaseController;

@Controller
@RequestMapping("/Resource")
public class ResourceController extends BaseController {

	@Autowired
	private ResourceService resourceService;

	@RequestMapping(value = "/LoadTree")
	@ResponseBody
	protected List<TreeDto> LoadTree(HttpServletRequest request, HttpServletResponse response, Model model) {
		List<Resource> list = resourceService.findByParent(null);
		List<TreeDto> result = new ArrayList<TreeDto>(list.size());
		for (Resource resource : list) {
			result.add(new TreeDto(resource));
		}
		return result;
	}

	@Override
	public Class<? extends BaseObjectImpl> getBaseObjectClass() {
		return ResourceImpl.class;
	}
	
}
