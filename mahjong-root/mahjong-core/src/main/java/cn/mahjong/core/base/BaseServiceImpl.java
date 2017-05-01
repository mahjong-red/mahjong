package cn.mahjong.core.base;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.mahjong.persist.base.BaseDao;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	@Autowired
	@Qualifier("baseDao")
	protected BaseDao dao;

	public void save(Object Object) {
		this.dao.save(Object);
	}

	public void saveOrUpdate(Object Object) {
		this.dao.saveOrUpdate(Object);
	}
	
	public void update(Object Object) {
		this.dao.update(Object);
	}

	public Object getObject(Class<? extends Object> clazz, long id) {
		return this.dao.getObject(clazz, id);
	}

	public Object getObject(String className, long id) {
		return this.dao.getObject(className, id);
	}

	public Object loadObject(Class<? extends Object> clazz, long id) {
		return this.dao.loadObject(clazz, id);
	}

	public Object loadObject(String className, long id) {
		return this.dao.loadObject(className, id);
	}

	public List<? extends Object> loadAll(Class<? extends Object> clazz) {
		return this.dao.loadAll(clazz);
	}

	public void delete(Object object) {
		this.dao.delete(object);
	}

	public void deleteAll(Class<? extends Object> clazz) {
		this.dao.deleteAll(clazz);
	}

	public List<?> find(Class<? extends Object> clazz, Map<String,Object> map, String mode) {
		return this.dao.find(clazz, map, mode);
	}
	
	public List<?> find(Class<? extends Object> clazz, String[] names, Object[] values, String mode) {
		return this.dao.find(clazz, names, values, mode);
	}
	
	public void attach(Object Object) {
		this.dao.attach(Object);
	}

	public void refresh(Object Object) {
		this.dao.refresh(Object);
	}
	
	public void merge(Object Object) {
		this.dao.merge(Object);
	}

}
