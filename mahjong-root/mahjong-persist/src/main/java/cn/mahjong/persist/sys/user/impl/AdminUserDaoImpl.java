package cn.mahjong.persist.sys.user.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import cn.mahjong.model.sys.user.AdminUser;
import cn.mahjong.model.sys.user.impl.AdminUserImpl;
import cn.mahjong.persist.base.impl.BaseDaoImpl;
import cn.mahjong.persist.sys.user.AdminUserDao;

@Repository("adminUserDao")
@SuppressWarnings("unchecked")
public class AdminUserDaoImpl extends BaseDaoImpl implements AdminUserDao{

	@Override
	public AdminUser loadByUsername(String username) {
		Criteria criteria = currentCriteria(AdminUserImpl.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.setMaxResults(1);

		List<AdminUser> list = criteria.list();
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}

}
