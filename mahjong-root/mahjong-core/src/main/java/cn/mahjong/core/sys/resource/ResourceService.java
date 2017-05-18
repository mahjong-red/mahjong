package cn.mahjong.core.sys.resource;

import java.util.List;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.model.sys.resource.Resource;

public interface ResourceService extends BaseService {
	
	public List<Resource> findByParent(Long parentId);

	public List<Resource> loadAllResourceWithRoleTx();
	
	
}
