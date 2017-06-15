package cn.mahjong.web.base.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.core.security.SecurityHelp;
import cn.mahjong.dto.RestResp;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.Bmo;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.model.base.impl.BmoImpl;
import cn.mahjong.model.sys.user.User;
import cn.mahjong.utils.search.PageData;
import cn.mahjong.utils.search.PageQuery;
import cn.mahjong.web.bind.BindingUtil;

public abstract class BaseController {
	
	@Autowired
	private BaseService baseService;
	
	private Class<? extends BaseObjectImpl> baseObjectClass;
	
	@RequestMapping
	protected String onSubmit(HttpServletRequest request, HttpServletResponse response, Model model) {
		String servletPath = request.getServletPath();
		return servletPath + servletPath;
	}
	
	@RequestMapping(value = "/Create",method = RequestMethod.POST)
	@ResponseBody
	protected RestResp create(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
		try {
			BaseObject baseObject = baseObjectClass.newInstance();
			BindingUtil.bindObject(baseObject, request, null);
			if (baseObject instanceof BmoImpl) {
				Bmo bmo = (BmoImpl)baseObject;
				Date now = new Date();
				User user = SecurityHelp.getCurrentUser();
				bmo.setCreateDate(now);
				bmo.setUpdateDate(now);
				bmo.setCreateUser(user);
				bmo.setUpdateUser(user);
			}
			this.baseService.save(baseObject);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			resp.setCode("1");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	
	@RequestMapping(value = "/Update",method = RequestMethod.POST)
	@ResponseBody
	protected RestResp update(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
		BaseObject baseObject = baseService.getObject(baseObjectClass, Long.parseLong(request.getParameter("id")));
		BindingUtil.bindObject(baseObject, request, null);
		if (baseObject instanceof BmoImpl) {
			Bmo bmo = (BmoImpl)baseObject;
			Date now = new Date();
			User user = SecurityHelp.getCurrentUser();
			bmo.setUpdateDate(now);
			bmo.setUpdateUser(user);
		}
		this.baseService.update(baseObject);
		return resp;
	}
	
	@RequestMapping(value = "/Delete")
	@ResponseBody
	protected RestResp delete(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
		String id = request.getParameter("id");
		String ids[] = id.split(",");
		for (String string : ids) {
			BaseObject baseObject = baseService.getObject(baseObjectClass, Long.parseLong(string));
			this.baseService.delete(baseObject);
		}
		return resp;
	}
	
	@RequestMapping(value = "/Save",method = RequestMethod.POST)
	@ResponseBody
	protected RestResp save(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
		try {
			BaseObject baseObject = baseObjectClass.newInstance();
			BindingUtil.bindObject(baseObject, request, null);
			this.baseService.saveOrUpdate(baseObject);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			resp.setCode("1");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
	
	@RequestMapping(value = "/Find")
	@ResponseBody
	protected PageData find(HttpServletRequest request, HttpServletResponse response, Model model) {
		PageQuery pageQuery = new PageQuery(baseObjectClass);
		BindingUtil.bindPageProperty(pageQuery, request);
		BindingUtil.bindSearchProperty(pageQuery, request);
		return new PageData((int)pageQuery.getTotal(), this.baseService.find(pageQuery));
	}
	
	public BaseController(){
		setBaseObjectClass(getBaseObjectClass());
	}

	public abstract Class<? extends BaseObjectImpl> getBaseObjectClass();

	public void setBaseObjectClass(Class<? extends BaseObjectImpl> baseObjectClass) {
		this.baseObjectClass = baseObjectClass;
	}
	
}
