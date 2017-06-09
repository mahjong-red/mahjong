package cn.mahjong.web.sys.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import cn.mahjong.model.sys.resource.Resource;

public class ResourceDto {
	
	private Resource target;

	public ResourceDto(Resource target) {
		this.target = target;
	}
	
	public long getId(){
		return target.getId();
	}
	
	public String getName(){
		return target.getName();
	}
	
	public String getUrl(){
		return target.getUrl();
	}
	
	public String getSequence(){
		return target.getSequence();
	}
	
	public String getResourceType(){
		return target.getResourceType().getVal().toString();
	}
	
	public String getResourceTypeDesc(){
		return target.getResourceType().getDescription();
	}
	
	public List<ResourceDto> getChildren(){
		if (!CollectionUtils.isEmpty(target.getChildren())) {
			List<ResourceDto> list = new ArrayList<ResourceDto>(target.getChildren().size());
			for (Resource item : target.getChildren()) {
				list.add(new ResourceDto(item));
			}
			return list;
		}
		return null;
	}
	
	
}
