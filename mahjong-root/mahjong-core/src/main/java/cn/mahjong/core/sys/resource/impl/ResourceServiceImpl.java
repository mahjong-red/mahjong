package cn.mahjong.core.sys.resource.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mahjong.core.base.impl.BaseServiceImpl;
import cn.mahjong.core.sys.resource.ResourceService;
import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.resource.impl.ResourceImpl;
import cn.mahjong.persist.sys.resource.ResourceDao;

@Service("resourceService")
public class ResourceServiceImpl extends BaseServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	public List<Resource> findByParent(Long parentId){
		return resourceDao.findByParent(parentId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Resource> loadAllResourceWithRoleTx() {
		List<Resource> list = (List<Resource>) baseDao.loadAll(ResourceImpl.class);
		for (Resource resource : list) {
			Hibernate.initialize(resource.getRoleSet());
		}
		return list;
	}
	
}
