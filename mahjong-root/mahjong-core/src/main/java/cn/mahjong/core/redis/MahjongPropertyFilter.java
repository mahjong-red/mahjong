package cn.mahjong.core.redis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class MahjongPropertyFilter extends SimpleBeanPropertyFilter {

	public Set<String> filterProperty = new HashSet<String>();
	
	@Override
	protected boolean include(BeanPropertyWriter writer) {
		return filter(writer);
	}

	@Override
	protected boolean include(PropertyWriter writer) {
		return filter(writer);
	}

	public boolean filter(PropertyWriter writer){
		return filterProperty!=null?!filterProperty.contains(writer.getName()):false;
	}

	public Set<String> getFilterProperty() {
		return filterProperty;
	}

	public void setFilterProperty(Set<String> filterProperty) {
		System.out.println(filterProperty.getClass().getName());
		this.filterProperty.addAll(filterProperty);
	}
}
