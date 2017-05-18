package cn.mahjong.persist.sys.user.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import cn.mahjong.model.sys.user.User;
import cn.mahjong.model.sys.user.impl.UserImpl;
import cn.mahjong.persist.base.impl.BaseDaoImpl;
import cn.mahjong.persist.sys.user.UserDao;

@Repository("userDao")
@SuppressWarnings("unchecked")
public class UserDaoImpl extends BaseDaoImpl implements UserDao{

	@Override
	public User loadByUsername(String username) {
		Criteria criteria = currentCriteria(UserImpl.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.setMaxResults(1);

		List<User> list = criteria.list();
		return CollectionUtils.isEmpty(list)?null:list.get(0);
	}

}
