package cn.mahjong.enums.persist;

public interface PersistEnum <E extends Enum<?>> {
	
	/**
	 * 存入数据库的值
	 * @return
	 */
	Integer getPersistedValue();
	
	/**
	 * 从数据库取出映射
	 * @param persistedValue
	 * @return
	 */
	E returnEnum(Integer persistedValue);
}
