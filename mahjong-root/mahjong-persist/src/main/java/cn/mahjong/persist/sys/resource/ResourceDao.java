package cn.mahjong.persist.sys.resource;

import java.util.List;

import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.persist.base.BaseDao;

public interface ResourceDao extends BaseDao {

	public List<Resource> findByParent(Long parentId);
}
