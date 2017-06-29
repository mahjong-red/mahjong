package cn.mahjong.web.sys.role.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.security.MahjongSecurityMetadata;
import cn.mahjong.core.sys.role.RoleService;
import cn.mahjong.dto.RestResp;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.model.sys.role.Role;
import cn.mahjong.model.sys.role.impl.RoleImpl;
import cn.mahjong.utils.search.PageData;
import cn.mahjong.utils.search.PageQuery;
import cn.mahjong.web.base.controller.BaseController;
import cn.mahjong.web.bind.BindingUtil;
import cn.mahjong.web.sys.role.RoleDto;

@Controller
@RequestMapping("/Role")
public class RoleController extends BaseController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private MahjongSecurityMetadata mahjongSecurityMetadata;
	
	@RequestMapping(value = "/Find")
	@ResponseBody
	protected PageData find(HttpServletRequest request, HttpServletResponse response, Model model) {
		return super.find(request, response, RoleDto.class);
	}

	@RequestMapping(value = "/InitRole")
	@ResponseBody
	protected RestResp initResource(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0", "", null);
		mahjongSecurityMetadata.initSecurityMeta();
		return resp;
	}

	@Override
	public Class<? extends BaseObjectImpl> getBaseObjectClass() {
		return RoleImpl.class;
	}
	
}
