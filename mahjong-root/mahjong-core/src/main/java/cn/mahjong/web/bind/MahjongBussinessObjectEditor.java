package cn.mahjong.web.bind;

import java.beans.PropertyEditorSupport;

import org.apache.commons.lang3.StringUtils;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.impl.BaseObjectImpl;

public class MahjongBussinessObjectEditor extends PropertyEditorSupport {

	private BaseService baseService;

	private Class<? extends BaseObjectImpl> clazz;

	public MahjongBussinessObjectEditor(Class<? extends BaseObjectImpl> clazz, BaseService baseService) {
		this.clazz = clazz;
		this.baseService = baseService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (StringUtils.isBlank(text)) {
			this.setValue(null);
		}
		else {
			long id = new Long(text).longValue();
			BaseObject businessObject = this.baseService.loadObject(this.clazz, id);
			this.setValue(businessObject);
		}
	}
	
	@Override
	public String getAsText() {
		BaseObject value = (BaseObject) getValue();
		return (value != null ? new Long(value.getId()).toString() : "");
	}
	
}
