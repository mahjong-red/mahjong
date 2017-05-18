package cn.mahjong.web.sys.user.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.model.sys.user.impl.UserImpl;
import cn.mahjong.utils.search.PageData;
import cn.mahjong.utils.search.PageQuery;
import cn.mahjong.utils.web.bind.BindingUtil;
import cn.mahjong.web.base.controller.BaseController;
import cn.mahjong.web.sys.user.UserDto;

@Controller
@RequestMapping("/User")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;
	

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/Find")
	@ResponseBody
	protected PageData find(HttpServletRequest request, HttpServletResponse response, Model model) {
		PageQuery pageQuery = new PageQuery(getBaseObjectClass());
		BindingUtil.bindPageProperty(pageQuery, request);
		BindingUtil.bindSearchProperty(pageQuery, request);
		List<User> list = (List<User>) userService.find(pageQuery);
		List<UserDto> resultList = new ArrayList<UserDto>();
		for (User user : list) {
			resultList.add(new UserDto(user));
		}
		return new PageData((int)pageQuery.getTotal(), resultList);
	}
	
	@Override
	public Class<? extends BaseObjectImpl> getBaseObjectClass() {
		return UserImpl.class;
	}
}
