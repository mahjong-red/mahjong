package cn.mahjong.utils.search;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 数据类型
 * 
 * @author lzq
 */
public enum DataType {

	/**
	 * Integer
	 */
	INTEGER,

	/**
	 * Long
	 */
	LONG,

	/**
	 * Short
	 */
	SHORT,

	/**
	 * DOUBLE
	 */
	DOUBLE,

	/**
	 * Float
	 */
	FLOAT,

	/**
	 * BigDecimal
	 */
	BIG_DECIMAL,

	/**
	 * 字符串
	 */
	STRING,

	/**
	 * 日期
	 */
	DATE,

	/**
	 * 布尔
	 */
	BOOLEAN,

	/**
	 * 对象
	 */
	OBJECT;

	public static DataType getDataType(Class<?> clazz) {
		if (Integer.class.equals(clazz)) {
			return INTEGER;
		}
		else if (Long.class.equals(clazz)) {
			return LONG;
		}
		else if (Short.class.equals(clazz)) {
			return SHORT;
		}
		else if (Double.class.equals(clazz)) {
			return DOUBLE;
		}
		else if (Float.class.equals(clazz)) {
			return FLOAT;
		}
		else if (BigDecimal.class.equals(clazz)) {
			return BIG_DECIMAL;
		}
		else if (String.class.equals(clazz)) {
			return STRING;
		}
		else if (Date.class.equals(clazz)) {
			return DATE;
		}
		else if (Boolean.class.equals(clazz)) {
			return BOOLEAN;
		}
		else {
			return OBJECT;
		}
	}

}
