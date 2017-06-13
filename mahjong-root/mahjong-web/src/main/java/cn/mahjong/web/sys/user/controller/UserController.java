package cn.mahjong.web.sys.user.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.security.SecurityHelp;
import cn.mahjong.core.sys.user.UserService;
import cn.mahjong.dto.RestResp;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.Bmo;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.model.base.impl.BmoImpl;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.model.sys.user.impl.UserImpl;
import cn.mahjong.utils.search.PageData;
import cn.mahjong.utils.search.PageQuery;
import cn.mahjong.web.base.controller.BaseController;
import cn.mahjong.web.bind.BindingUtil;
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
		return new PageData(pageQuery.getTotal(), BindingUtil.convertToDtoList(list, User.class, UserDto.class));
	}
	
	@Override
	public Class<? extends BaseObjectImpl> getBaseObjectClass() {
		return UserImpl.class;
	}
	
	@RequestMapping(value = "/Create",method = RequestMethod.POST)
	@ResponseBody
	protected RestResp create(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
		User user = new UserImpl();
		BindingUtil.bindObject(user, request, null);
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();
		user.setPassword(encoder.encodePassword(user.getPassword(), user.getUsername()));
		userService.save(user);
		return resp;
	}
	
	@RequestMapping(value = "/CheckUserName")
	@ResponseBody
	protected RestResp checkUserName(HttpServletRequest request, HttpServletResponse response, Model model,String username) {
		RestResp resp = new RestResp("0","ok",null);
		User user = userService.loadByUserName(username);
		if (user != null) {
			resp.setCode("1");
			resp.setMsg("用户名已存在");
		}
		return resp;
	}
}
