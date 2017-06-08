package cn.mahjong.web.base.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		String servletPath = request.getServletPath();
		return servletPath + servletPath;
	}
	
//	@RequestMapping(value="{operate}",method = RequestMethod.GET)
//	protected String operate(@PathVariable("operate") String operate,HttpServletRequest request, Model model) {
//		String servletPath = request.getServletPath();
//		String id = request.getParameter("id");
//		if (StringUtils.isNotEmpty(id)) {
//			model.addAttribute("object", baseService.loadObject(baseObjectClass, Long.parseLong(id)));
//		}
//		return servletPath.substring(1, servletPath.indexOf(File.separatorChar,1)) + File.separatorChar + servletPath.replace(File.separator, "");
//	}
	
	@RequestMapping(value = "/Create",method = RequestMethod.POST)
	@ResponseBody
	protected RestResp create(HttpServletRequest request, HttpServletResponse response, Model model) {
		RestResp resp = new RestResp("0","ok",null);
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
