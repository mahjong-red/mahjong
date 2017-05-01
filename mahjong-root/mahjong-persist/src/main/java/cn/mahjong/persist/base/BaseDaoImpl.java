package cn.mahjong.persist.base;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao{

	public void save(Object object) {
		this.getHibernateTemplate().save(object);
	}

	public void saveOrUpdate(Object object) {
		this.getHibernateTemplate().saveOrUpdate(object);
	}

	public void update(Object object) {
		this.getHibernateTemplate().update(object);
	}

	public Object getObject(Class<? extends Object> clazz, long id) {
		return this.getHibernateTemplate().get(clazz, id);
	}

	public Object getObject(String className, long id) {
		return (Object) this.getHibernateTemplate().get(className, id);
	}

	public Object loadObject(Class<? extends Object> clazz, long id) {
		return this.getHibernateTemplate().load(clazz, id);
	}

	public Object loadObject(String className, long id) {
		return (Object) this.getHibernateTemplate().load(className, id);
	}

	public List<? extends Object> loadAll(Class<? extends Object> clazz) {
		return this.getHibernateTemplate().loadAll(clazz);
	}

	public void delete(Object Object) {
		this.getHibernateTemplate().delete(Object);
	}

	public void deleteAll(Class<? extends Object> clazz) {
		String hql = "delete " + clazz.getName();
		Query query = this.currentSession().createQuery(hql);
		query.executeUpdate();
	}

	public Criteria currentCriteria(Class<? extends Object> clazz) {
		return this.currentSession().createCriteria(clazz);
	}

	public Criteria currentCriteria(Class<? extends Object> clazz, String alias) {
		return this.currentSession().createCriteria(clazz, alias);
	}

	public Criteria currentCriteria(String entityName) {
		return this.currentSession().createCriteria(entityName);
	}

	public void flush() {
		this.getHibernateTemplate().flush();
	}

	public void attach(Object Object) {
		this.currentSession().buildLockRequest(LockOptions.NONE).lock(Object);
	}

	public void refresh(Object Object) {
		this.currentSession().refresh(Object);
	}

	public void merge(Object Object) {
		this.currentSession().merge(Object);
	}

	@Autowired
	public void setSessionFactoryOverride(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public List<Object> find(Class<? extends Object> clazz, Map<String, Object> map, String mode) {
		return null;
	}

	@Override
	public List<Object> find(Class<? extends Object> clazz, String[] names, Object[] values, String mode) {
		return null;
	}
}
