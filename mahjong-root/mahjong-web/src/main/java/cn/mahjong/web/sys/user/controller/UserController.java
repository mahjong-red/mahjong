package cn.mahjong.web.sys.user.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.security.SecurityHelp;
import cn.mahjong.core.sys.role.RoleService;
import cn.mahjong.core.sys.user.AdminUserService;
import cn.mahjong.dto.RestResp;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.model.sys.role.impl.RoleImpl;
import cn.mahjong.model.sys.user.AdminUser;
import cn.mahjong.model.sys.user.impl.AdminUserImpl;
import cn.mahjong.utils.search.PageData;
import cn.mahjong.web.base.controller.BaseController;
import cn.mahjong.web.bind.BindingUtil;
import cn.mahjong.web.sys.user.AdminUserDto;

@Controller
@RequestMapping("/User")
public class UserController extends BaseController {

	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private RoleService roleService;
	
	@RequestMapping
	protected String onSubmit(HttpServletRequest request, HttpServletResponse response, Model model) {
		model.addAttribute("roleList", roleService.loadAll(RoleImpl.class));
		String servletPath = request.getServletPath();
		return servletPath + servletPath;
	}
	
	@RequestMapping(value = "/Find")
	@ResponseBody
	protected PageData find(HttpServletRequest request, HttpServletResponse response, Model model) {
		return super.find(request, response, AdminUserDto.class);
	}
	
	@Override
	public Class<? extends BaseObjectImpl> getBaseObjectClass() {
		return AdminUserImpl.class;
	}
	
	@RequestMapping(value = "/Create",method = RequestMethod.POST)
	@ResponseBody
	protected RestResp create(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
		AdminUser user = new AdminUserImpl();
		BindingUtil.bindObject(user, request, null);
		if (StringUtils.isNotEmpty(user.getPassword()) && user.getPassword().length() > 5) {
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			user.setPassword(encoder.encodePassword(user.getPassword(), user.getUsername()));
			Date now = new Date();
			AdminUser user1 = SecurityHelp.getCurrentUser();
			user.setCreateDate(now);
			user.setUpdateDate(now);
			user.setCreateUser(user1);
			user.setUpdateUser(user1);
			adminUserService.save(user);
		}else{
			resp.setCode("1");
			resp.setMsg("密码至少6位，请重新输入。");
			return resp;
		}
		return resp;
	}
	
	@RequestMapping(value = "/Update",method = RequestMethod.POST)
	@ResponseBody
	protected RestResp update(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
		String password = request.getParameter("password");
		AdminUser user = (AdminUser) adminUserService.getObject(getBaseObjectClass(), Long.parseLong(request.getParameter("id")));
		BindingUtil.bindObject(user, request, null);
		if (StringUtils.isNotBlank(password)) {
			if (password.length() <6) {
				resp.setCode("1");
				resp.setMsg("密码至少6位，请重新输入。");
				return resp;
			}
			Md5PasswordEncoder encoder = new Md5PasswordEncoder();
			user.setPassword(encoder.encodePassword(password, user.getUsername()));
		}
		Date now = new Date();
		user.setUpdateDate(now);
		user.setUpdateUser(SecurityHelp.getCurrentUser());
		adminUserService.update(user);
		return resp;
	}
	
	@RequestMapping(value = "/CheckUserName")
	@ResponseBody
	protected RestResp checkUserName(HttpServletRequest request, HttpServletResponse response, Model model,String username) {
		RestResp resp = new RestResp("0","ok",null);
		AdminUser user = adminUserService.loadByUserName(username);
		if (user != null) {
			resp.setCode("1");
			resp.setMsg("用户名已存在");
		}
		return resp;
	}
}
