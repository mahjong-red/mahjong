package cn.mahjong.core.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

public class MahjongObjectMapper extends ObjectMapper {

	private static final long serialVersionUID = 1L;

	public void setPropertyFilter(MahjongPropertyFilter propertyFilter) {
		SimpleFilterProvider filterProvider = new SimpleFilterProvider();
		filterProvider.setDefaultFilter(propertyFilter);
		filterProvider.addFilter("test", propertyFilter);
		super.setFilters(filterProvider);
	}
}
