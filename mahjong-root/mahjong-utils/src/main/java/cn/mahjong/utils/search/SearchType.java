package cn.mahjong.utils.search;

/**
 * 搜索类型
 * 
 * @author lzq
 */
public enum SearchType {

	/**
	 * 等于
	 */
	EQUAL,

	/**
	 * between
	 */
	RANGE,

	/**
	 * 大于
	 */
	GREATER,

	/**
	 * 小于
	 */
	LESS,

	/**
	 * 大于等于
	 */
	GREATER_EQUAL,

	/**
	 * 小于等于
	 */
	LESS_EQUAL,
	
	/**
	 * 或者
	 */
	OR

}
