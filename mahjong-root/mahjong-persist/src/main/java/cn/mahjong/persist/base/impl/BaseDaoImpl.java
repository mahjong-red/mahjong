package cn.mahjong.persist.base.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.persist.base.BaseDao;
import cn.mahjong.utils.search.PageQuery;
import cn.mahjong.utils.search.SearchItem;
import cn.mahjong.utils.search.SearchType;

@Repository("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao{

	public void save(BaseObject baseObject) {
		this.getHibernateTemplate().save(baseObject);
	}

	public void saveOrUpdate(BaseObject baseObject) {
		this.getHibernateTemplate().saveOrUpdate(baseObject);
	}

	public void update(BaseObject baseObject) {
		this.getHibernateTemplate().update(baseObject);
	}

	public BaseObject getObject(Class<? extends BaseObjectImpl> clazz, long id) {
		return this.getHibernateTemplate().get(clazz, id);
	}

	public BaseObject getObject(String className, long id) {
		return (BaseObject) this.getHibernateTemplate().get(className, id);
	}

	public BaseObject loadObject(Class<? extends BaseObjectImpl> clazz, long id) {
		return this.getHibernateTemplate().load(clazz, id);
	}

	public BaseObject loadObject(String className, long id) {
		return (BaseObject) this.getHibernateTemplate().load(className, id);
	}

	public List<? extends BaseObject> loadAll(Class<? extends BaseObjectImpl> clazz) {
		return this.getHibernateTemplate().loadAll(clazz);
	}

	public void delete(BaseObject baseObject) {
		if (baseObject == null) {
			return;
		}
		this.getHibernateTemplate().delete(baseObject);
	}
	
	public void deleteBatch(List<BaseObject> list){
		this.getHibernateTemplate().deleteAll(list);
	}

	public void deleteAll(Class<? extends BaseObjectImpl> clazz) {
		String hql = "delete " + clazz.getName();
		Query query = this.currentSession().createQuery(hql);
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<BaseObject> find(PageQuery pageQuery) {
		this.initTotal(pageQuery);

		Criteria criteria = this.currentSession().createCriteria(pageQuery.getTargetClass());

		Set<SearchItem> searchItems = pageQuery.getSearchIteams();

		for (SearchItem searchItem : searchItems) {
			if (SearchType.RANGE.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.between(searchItem.getItemName(), searchItem.getValue1(), searchItem.getValue2()));
			} else if (SearchType.GREATER.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.gt(searchItem.getItemName(), searchItem.getValue1()));
			} else if (SearchType.GREATER_EQUAL.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.ge(searchItem.getItemName(), searchItem.getValue1()));
			} else if (SearchType.LESS.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.lt(searchItem.getItemName(), searchItem.getValue1()));
			} else if (SearchType.LESS_EQUAL.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.le(searchItem.getItemName(), searchItem.getValue1()));
			} else if (SearchType.LIKE.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.like(searchItem.getItemName(), searchItem.getValue1().toString(), MatchMode.ANYWHERE));
			} else {
				criteria.add(Restrictions.eq(searchItem.getItemName(), searchItem.getValue1()));
			}
		}

		criteria.setFirstResult(pageQuery.getRows()*(pageQuery.getPage()-1));
		criteria.setMaxResults(pageQuery.getRows());

		String sort = StringUtils.isBlank(pageQuery.getSort()) ? "id" : pageQuery.getSort();

		if ("desc".equals(pageQuery.getOrder())) {
			criteria.addOrder(Order.desc(sort));
		} else {
			criteria.addOrder(Order.asc(sort));
		}
		return criteria.list();
	}

	protected void initTotal(PageQuery pageQuery) {
		Criteria criteria = this.currentSession().createCriteria(pageQuery.getTargetClass());
		Set<SearchItem> searchItems = pageQuery.getSearchIteams();

		for (SearchItem searchItem : searchItems) {
			if (searchItem.getValue1() instanceof String) {
				criteria.add(Restrictions.ilike(searchItem.getItemName(), ((String) searchItem.getValue1()).trim(), MatchMode.ANYWHERE));
			} else if (SearchType.RANGE.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.ge(searchItem.getItemName(), searchItem.getValue1()));
				criteria.add(Restrictions.lt(searchItem.getItemName(), searchItem.getValue2()));
			} else if (SearchType.GREATER.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.gt(searchItem.getItemName(), searchItem.getValue1()));
			} else if (SearchType.GREATER_EQUAL.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.ge(searchItem.getItemName(), searchItem.getValue1()));
			} else if (SearchType.LESS.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.lt(searchItem.getItemName(), searchItem.getValue1()));
			} else if (SearchType.LESS_EQUAL.equals(searchItem.getSearchType())) {
				criteria.add(Restrictions.le(searchItem.getItemName(), searchItem.getValue1()));
			} else {
				criteria.add(Restrictions.eq(searchItem.getItemName(), searchItem.getValue1()));
			}
		}
		criteria.setProjection(Projections.rowCount());
		pageQuery.setTotal((Long) criteria.uniqueResult());
	}

	public Criteria currentCriteria(Class<? extends BaseObjectImpl> clazz) {
		return this.currentSession().createCriteria(clazz);
	}

	public Criteria currentCriteria(Class<? extends BaseObjectImpl> clazz, String alias) {
		return this.currentSession().createCriteria(clazz, alias);
	}

	public Criteria currentCriteria(String entityName) {
		return this.currentSession().createCriteria(entityName);
	}

	public void flush() {
		this.getHibernateTemplate().flush();
	}

	public void attach(BaseObject baseObject) {
		this.currentSession().buildLockRequest(LockOptions.NONE).lock(baseObject);
	}

	public void refresh(BaseObject baseObject) {
		this.currentSession().refresh(baseObject);
	}

	public void merge(BaseObject baseObject) {
		this.currentSession().merge(baseObject);
	}

	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	public void deleteBatch(List<Long> idlist,Class<? extends BaseObject> c) {
		StringUtils.join(idlist, ',');
		StringBuilder hql = new StringBuilder("delete from ");
		hql.append(c.getSimpleName());
		hql.append(" where id in (");
		hql.append(StringUtils.join(idlist, ','));
		hql.append(")");
		Session session = this.currentSession();
		Query query = session.createQuery(hql.toString());
		query.executeUpdate();
	}
	
}
