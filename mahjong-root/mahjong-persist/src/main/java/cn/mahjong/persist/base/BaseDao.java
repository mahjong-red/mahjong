package cn.mahjong.persist.base;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;

public interface BaseDao {

	public void save(Object Object);

	public void saveOrUpdate(Object Object);
	
	public void update(Object Object);

	public Object getObject(Class<? extends Object> clazz, long id);

	public Object getObject(String className, long id);

	public Object loadObject(Class<? extends Object> clazz, long id);

	public Object loadObject(String className, long id);

	public List<? extends Object> loadAll(Class<? extends Object> clazz);

	public void delete(Object Object);

	public void deleteAll(Class<? extends Object> clazz);

	public Criteria currentCriteria(Class<? extends Object> clazz);
	
	public Criteria currentCriteria(Class<? extends Object> clazz, String alias);

	public List<Object> find(Class<? extends Object> clazz, Map<String,Object> map, String mode);
	
	public List<Object> find(Class<? extends Object> clazz, String[] names, Object[] values, String mode);
	
	public Criteria currentCriteria(String entityName);

	public void flush();
	
	public void attach(Object Object);

	public void refresh(Object Object);
	
	public void merge(Object Object);
	

}
