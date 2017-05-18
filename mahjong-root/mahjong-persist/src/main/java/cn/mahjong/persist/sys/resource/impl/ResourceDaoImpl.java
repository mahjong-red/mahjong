package cn.mahjong.persist.sys.resource.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import cn.mahjong.model.sys.resource.Resource;
import cn.mahjong.model.sys.resource.impl.ResourceImpl;
import cn.mahjong.persist.base.impl.BaseDaoImpl;
import cn.mahjong.persist.sys.resource.ResourceDao;

@Repository("resourceDao")
public class ResourceDaoImpl extends BaseDaoImpl implements ResourceDao{
	
	@SuppressWarnings("unchecked")
	public List<Resource> findByParent(Long parentId){
		Criteria criteria = this.currentSession().createCriteria(ResourceImpl.class);
		if (parentId != null && parentId > 0) {
			criteria.add(Restrictions.eq("parent.id", parentId));
		}else {
			criteria.add(Restrictions.isNull("parent"));
		}
		criteria.addOrder(Order.asc("sequence"));
		return criteria.list();
	}
}
