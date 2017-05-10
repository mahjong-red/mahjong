package cn.mahjong.core.base.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mahjong.core.base.BaseService;
import cn.mahjong.model.base.BaseObject;
import cn.mahjong.model.base.Bmo;
import cn.mahjong.model.base.impl.BaseObjectImpl;
import cn.mahjong.persist.base.BaseDao;
import cn.mahjong.utils.search.PageQuery;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	@Autowired
	protected BaseDao baseDao;

	public void save(BaseObject baseObject) {
		baseDao.save(baseObject);
	}

	public void save(Bmo bmo) {
		Date now = new Date();
		bmo.setCreateDate(now);
		bmo.setUpdateDate(now);
		baseDao.save(bmo);
	}
	
	public void saveOrUpdate(BaseObject baseObject) {
		baseDao.saveOrUpdate(baseObject);
	}
	
	public void update(BaseObject baseObject) {
		baseDao.update(baseObject);
	}

	public void update(Bmo bmo) {
		Date now = new Date();
		bmo.setUpdateDate(now);
		baseDao.save(bmo);
	}
	
	public BaseObject getObject(Class<? extends BaseObjectImpl> clazz, long id) {
		return baseDao.getObject(clazz, id);
	}

	public BaseObject getObject(String className, long id) {
		return baseDao.getObject(className, id);
	}

	public BaseObject loadObject(Class<? extends BaseObjectImpl> clazz, long id) {
		return baseDao.loadObject(clazz, id);
	}

	public BaseObject loadObject(String className, long id) {
		return baseDao.loadObject(className, id);
	}

	public List<? extends BaseObject> loadAll(Class<? extends BaseObjectImpl> clazz) {
		return baseDao.loadAll(clazz);
	}

	public void delete(BaseObject object) {
		baseDao.delete(object);
	}

	public void deleteAll(Class<? extends BaseObjectImpl> clazz) {
		baseDao.deleteAll(clazz);
	}

	public List<? extends BaseObject> find(PageQuery pageQuery) {
		return baseDao.find(pageQuery);
	}
	
	public void attach(BaseObject baseObject) {
		baseDao.attach(baseObject);
	}

	public void refresh(BaseObject baseObject) {
		baseDao.refresh(baseObject);
	}
	
	public void merge(BaseObject baseObject) {
		baseDao.merge(baseObject);
	}

}
