package cn.mahjong.web.bind;

import java.beans.PropertyEditorSupport;
import java.util.Collection;

import org.apache.commons.lang3.StringUtils;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.impl.BaseObjectImpl;

public class MahjongCollectionStringArrayEditor extends PropertyEditorSupport {

	@SuppressWarnings({ "rawtypes", "unused" })
	private final Class<? extends Collection> collectionType;
	
	private BaseService baseService;
	
	private Class<? extends BaseObjectImpl> targetClass;

	@SuppressWarnings("rawtypes")
	public MahjongCollectionStringArrayEditor(Class<? extends Collection> collectionType, Class<? extends BaseObjectImpl> targetClass, BaseService baseService) {
		this.collectionType = collectionType;
		this.baseService = baseService;
		this.targetClass = targetClass;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		String[] idStrArray = StringUtils.split(text, ",");
		Collection value = (Collection) this.getValue();
		value.clear();
		
		for (String idStr : idStrArray) {
			long id = new Long(idStr).longValue();
			BaseObject object = this.baseService.loadObject(targetClass, id);
			value.add(object);
		}
	}
}
