package cn.mahjong.web.bind;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import cn.mahjong.enums.persist.PersistEnum;

public class CustomEnumEditor extends PropertyEditorSupport {

	private final Class<PersistEnum<?>> enumClass;

	private final boolean allowEmpty;

	public CustomEnumEditor(Class<PersistEnum<?>> enumClass, boolean allowEmpty) {
		this.enumClass = enumClass;
		this.allowEmpty = allowEmpty;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if (this.allowEmpty && !StringUtils.hasText(text)) {
			setValue(null);
		} else {
			PersistEnum<?> es[] = enumClass.getEnumConstants();
			setValue(es[0].returnEnum(Integer.parseInt(text)));
		}
	}

	@Override
	public String getAsText() {
		PersistEnum<?> es[] = enumClass.getEnumConstants();
		return String.valueOf(es[0].getPersistedValue());
	}
}
