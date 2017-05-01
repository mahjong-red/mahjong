package cn.mahjong.enums.persist;

public interface PersistEnum <E extends Enum<?>> {
	
	Integer getPersistedValue();
	
	E returnEnum(Integer persistedValue);
}
