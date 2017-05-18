package cn.mahjong.web.base.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.dto.RestResp;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.utils.search.PageData;
import cn.mahjong.utils.search.PageQuery;
import cn.mahjong.utils.web.bind.BindingUtil;

public abstract class BaseController {
	
	@Autowired
	private BaseService baseService;
	
	private Class<? extends BaseObjectImpl> baseObjectClass;
	
	@RequestMapping
	protected String onSubmit(HttpServletRequest request, HttpServletResponse response, Model model) {
		String controllerName = request.getServletPath().substring(1);
		return controllerName + "/" + controllerName;
	}
	
	@RequestMapping(value = "/Create")
	@ResponseBody
	protected RestResp create(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0",null,null);
		try {
			BaseObject baseObject = baseObjectClass.newInstance();
			BindingUtil.bindObject(baseObject, request, null);
			this.baseService.save(baseObject);
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