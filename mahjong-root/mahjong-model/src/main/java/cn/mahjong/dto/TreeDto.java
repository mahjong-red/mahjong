package cn.mahjong.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

import cn.mahjong.model.sys.resource.Resource;

public class TreeDto {
	
	private long id;
	
	private String text;
	
	private String iconCls;
	
	private String state;
	
	private List<TreeDto> children;
	
	private Map<String, String> attributes;
	
	private boolean checked = false;

	public TreeDto(long id, String text,String iconCls, String state, List<TreeDto> children, Map<String, String> attributes, boolean checked) {
		this.id = id;
		this.text = text;
		this.iconCls = iconCls;
		this.state = state;
		this.children = children;
		this.attributes = attributes;
		this.checked = checked;
	}

	public TreeDto(Resource resource) {
		this.id = resource.getId();
		this.iconCls = resource.getIconCls();
		this.text = resource.getName();
		this.attributes = new HashMap<String, String>();
		attributes.put("url", resource.getUrl());
		attributes.put("parentId", resource.getParent()!=null?String.valueOf(resource.getParent().getId()):null);
		attributes.put("sequence", resource.getSequence());
		attributes.put("resourceType", resource.getResourceType().getDescription());
		if (!CollectionUtils.isEmpty(resource.getChildren())) {
			children = new ArrayList<TreeDto>(resource.getChildren().size());
			for (Resource item : resource.getChildren()) {
				children.add(new TreeDto(item));
			}
		}
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<TreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<TreeDto> children) {
		this.children = children;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
